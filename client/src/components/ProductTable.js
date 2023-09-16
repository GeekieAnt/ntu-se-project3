import {
  Table,
  TableBody,
  TableCell,
  TableContainer,
  TableHead,
  TableRow,
  Paper,
  Button,
} from "@mui/material";
import React, { useEffect, useState } from "react";
import api from "../Utils/api";
import UpdateProduct from "./UpdateProduct";

const ProductTable = () => {
  const [products, setProducts] = useState([]);
  const [category, setCategory] = useState("");
  const [open, setOpen] = React.useState(false);
  const [id, setId] = useState("");
  const handleClose = () => setOpen(false);

  const handleDelete = (id) => {
    api.delete(`product/${id}`);
  };

  const handleOpen = (id, categoryName) => {
    setCategory(categoryName);
    setId(id);
    setOpen(true);
  };

  useEffect(() => {
    api.get(`product`).then(({ data }) => setProducts(data));
  }, [products]);

  return (
    <>
      <TableContainer component={Paper}>
        <Table>
          <TableHead>
            <TableRow>
              <TableCell>Product</TableCell>
              <TableCell align="center">Photo</TableCell>
              <TableCell align="right">Description</TableCell>
              <TableCell align="center">Sold</TableCell>
              <TableCell align="center">Price</TableCell>
              <TableCell align="center">Quantity</TableCell>
              <TableCell align="center">Category</TableCell>
              <TableCell align="center">Action</TableCell>
            </TableRow>
          </TableHead>
          <TableBody>
            {products.map((row) => (
              <TableRow
                key={row.id}
                sx={{ "&:last-child td, &:last-child th": { border: 0 } }}
              >
                <TableCell component="th" scope="row">
                  {row.name}
                </TableCell>
                <TableCell align="center">
                  <img src={row.photo} alt="product" height={50} />
                </TableCell>
                <TableCell align="right">{row.description}</TableCell>
                <TableCell align="center">{row.sold}</TableCell>
                <TableCell align="center">{row.price}</TableCell>
                <TableCell align="center">{row.quantity}</TableCell>
                <TableCell align="center">{row.category["name"]}</TableCell>
                <TableCell align="center">
                  <Button onClick={() => handleDelete(row.id)}>Delete</Button>
                  <Button
                    onClick={() => handleOpen(row.id, row.category["name"])}
                  >
                    Edit
                  </Button>
                </TableCell>
              </TableRow>
            ))}
          </TableBody>
        </Table>
      </TableContainer>
      <UpdateProduct
        setOpen={setOpen}
        open={open}
        handleClose={handleClose}
        id={id}
        category={category}
        setCategory={setCategory}
      />
    </>
  );
};
export default ProductTable;
