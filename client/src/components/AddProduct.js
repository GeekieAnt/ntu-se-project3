import React, { useEffect, useState } from "react";
import {
  TextField,
  Button,
  Box,
  FormControl,
  InputLabel,
  Select,
  MenuItem,
  LinearProgress,
} from "@mui/material";
import api from "../Utils/api";

const AddProduct = () => {
  const [file, setFile] = React.useState(null);
  const [loading, setLoading] = useState(false);
  const [fileValue, setFileValue] = React.useState("");
  const [categories, setCategories] = useState([]);
  const [category, setCategory] = useState("");
  const [formData, setFormData] = useState({
    name: "",
    description: "",
    price: 0,
    quantity: 0,
    sold: 0,
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

  const handleFileUpload = async () => {
    const uploadData = new FormData();
    uploadData.append("image", file);
    let response = await api.post(`upload`, uploadData);
    let urlString = response.data;

    return urlString;
  };

  const clearForm = () => {
    setFormData({
      name: "",
      description: "",
      price: 0,
      quantity: 0,
      sold: 0,
      photo: "",
    });
    setFileValue("");
  };

  const handleAdd = async (event) => {
    event.preventDefault();
    setLoading(true);
    const id = getIdFromCategory(category);

    await handleFileUpload().then((response) => {
      api
        .post(`product/category/${id}`, {
          name: formData.name,
          description: formData.description,
          price: formData.price,
          quantity: formData.quantity,
          sold: 0,
          photo: response,
        })
        .then((response) => {
          console.log(response);
          setLoading(false);
          clearForm();
        })
        .catch((error) => console.log(error))
        .finally(() => setLoading(false));
    });
  };

  useEffect(() => {
    api
      .get(`category`)
      .then(({ data }) => setCategories(data))
      .catch((response) => console.log(response));
  }, []);

  return (
    <>
      {loading && (
        <Box sx={{ width: "100%", mb: 2 }}>
          <LinearProgress />
        </Box>
      )}
      <Box sx={{ display: "flex", flexDirection: "column" }}>
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
          onClick={handleAdd}
          variant="contained"
          sx={{ width: 200, mt: 2 }}
        >
          ADD
        </Button>
      </Box>
    </>
  );
};

export default AddProduct;
