import React, { useEffect, useState } from "react";
import {
  TextField,
  Button,
  Box,
  FormControl,
  InputLabel,
  Select,
  MenuItem,
  Modal,
} from "@mui/material";
import api from "../Utils/api";

const style = {
  position: "absolute",
  top: "50%",
  left: "50%",
  transform: "translate(-50%, -50%)",
  bgcolor: "background.paper",
  p: 4,
  display: "flex",
  flexDirection: "column",
};

const UpdateProduct = ({ category, setCategory, handleClose, open, id }) => {
  const [file, setFile] = React.useState(null);
  const [fileValue, setFileValue] = React.useState("");
  const [categories, setCategories] = useState([]);
  const [formData, setFormData] = useState({
    name: "",
    description: "",
    price: 0,
    quantity: 0,
    sold: 0,
    photo: "",
    category: {
      id: "",
      name: "",
    },
  });

  const handleFileChange = (e) => {
    setFileValue(e.target.value);
    setFile(e.target.files[0]);
  };

  const getIdFromCategory = (category) => {
    for (const item of categories) {
      if (item.name === category) {
        return item.id;
      }
    }
    return null;
  };

  const handleChange = (event) => {
    setFormData({
      ...formData,
      [event.target.id]: event.target.value,
    });
  };

  const handleUpdate = (event) => {
    event.preventDefault();
    const categoryId = getIdFromCategory(category);
    const uploadData = new FormData();
    uploadData.append("image", file);

    api.post(`upload`, uploadData).then(({ data }) => {
      const urlString = data;
      console.log(data);
      console.log(urlString);
      api
        .put(`product/${id}`, {
          name: formData.name,
          description: formData.description,
          price: formData.price,
          quantity: formData.quantity,
          photo: urlString,
          category: {
            id: categoryId,
            name: category,
          },
        })
        .then((response) => console.log(response))
        .catch((error) => console.log(error));
    });
  };

  const handleProductRefresh = () => {
    api.get(`product/${id}`).then(({ data }) => {
      setFormData(data);
      console.log(formData);
    });
  };

  useEffect(() => {
    handleProductRefresh();
  }, [id]);

  useEffect(() => {
    api
      .get(`category`)
      .then(({ data }) => setCategories(data))
      .catch((response) => console.log(response));
  }, []);

  return (
    <Modal open={open} onClose={handleClose}>
      <Box sx={style}>
        <TextField
          label="name"
          variant="outlined"
          id="name"
          value={formData.name}
          onChange={handleChange}
          InputLabelProps={{ shrink: true }}
        />
        <TextField
          sx={{ mt: 1 }}
          label="description"
          variant="outlined"
          id="description"
          value={formData.description}
          onChange={handleChange}
          InputLabelProps={{ shrink: true }}
        />
        <TextField
          sx={{ mt: 1 }}
          label="price"
          variant="outlined"
          id="price"
          value={formData.price}
          onChange={handleChange}
          InputLabelProps={{ shrink: true }}
        />
        <TextField
          sx={{ mt: 1 }}
          label="quantity"
          variant="outlined"
          id="quantity"
          value={formData.quantity}
          onChange={handleChange}
          InputLabelProps={{ shrink: true }}
        />
        <TextField
          sx={{ mt: 1 }}
          label="image"
          type="file"
          id="image"
          name="image"
          onChange={handleFileChange}
          value={fileValue}
          variant="outlined"
          InputLabelProps={{ shrink: true }}
        />
        <FormControl sx={{ mt: 1 }}>
          <InputLabel>Category</InputLabel>
          <Select
            id="category"
            label="category"
            value={category}
            onChange={(e) => setCategory(e.target.value)}
          >
            {categories.map((item) => (
              <MenuItem key={item.id} value={item.name}>
                {item.name}
              </MenuItem>
            ))}
          </Select>
        </FormControl>
        <Button
          onClick={handleUpdate}
          variant="contained"
          sx={{ width: 200, mt: 2 }}
        >
          UPDATE
        </Button>
      </Box>
    </Modal>
  );
};

export default UpdateProduct;
