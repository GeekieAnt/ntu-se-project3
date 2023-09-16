import { TextField, Button, Box } from "@mui/material";
import React, { useEffect, useState } from "react";
import api from "../Utils/api";
import DisplayCategory from "./DisplayCategory";

const AddCateogory = () => {
  const [category, setCategory] = useState("");
  const [categories, setCategories] = useState([]);

  const handleAdd = () => {
    api
      .post(`category`, {
        name: category,
      })
      .then((response) => {
        console.log(response);
        refreshCategory();
      });
  };

  const refreshCategory = () => {
    api
      .get(`category`)
      .then(({ data }) => setCategories(data))
      .catch((response) => console.log(response));
  };

  useEffect(() => {
    refreshCategory();
  }, []);

  return (
    <>
      <Box sx={{ display: "flex", flexDirection: "column" }}>
        <TextField
          label="category"
          variant="outlined"
          value={category}
          onChange={(e) => setCategory(e.target.value)}
        />
        <Button
          variant="contained"
          sx={{ width: 200, mt: 2 }}
          onClick={handleAdd}
        >
          Add
        </Button>
        <DisplayCategory
          categories={categories}
          refreshCategory={refreshCategory}
        />
      </Box>
    </>
  );
};

export default AddCateogory;
