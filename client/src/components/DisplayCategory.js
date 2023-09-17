import React, { useState } from "react";
import { Chip, Stack, Modal, Box, TextField, Button } from "@mui/material";
import api from "../Utils/api";

const style = {
  position: "absolute",
  top: "50%",
  left: "50%",
  transform: "translate(-50%, -50%)",
  width: 400,
  height: 200,
  bgcolor: "background.paper",
  p: 4,
};

const DisplayCategory = ({ categories, refreshCategory }) => {
  const [open, setOpen] = React.useState(false);
  const [category, setCategory] = useState("");
  const [id, setId] = useState("");

  const handleDelete = (id) => {
    console.log(id);
    api
      .delete(`category/${id}`)
      .then(() => refreshCategory())
      .catch((response) => console.log(response));
  };

  const handleOpen = (id, name) => {
    setId(id);
    setCategory(name);
    setOpen(true);
  };
  const handleClose = () => setOpen(false);

  const handleUpdate = () => {
    api
      .put(`category/${id}`, {
        name: category,
      })
      .then(() => {
        handleClose();
        refreshCategory();
      })
      .catch((error) => console.log(error));
  };

  return (
    <>
      <Stack direction="row" spacing={1} sx={{ mt: 3 }}>
        {categories.map((item) => (
          <Chip
            key={item.id}
            label={item.name}
            variant="outlined"
            onClick={() => handleOpen(item.id, item.name)}
            onDelete={() => handleDelete(item.id)}
          />
        ))}
      </Stack>
      <Modal open={open} onClose={handleClose}>
        <Box sx={style}>
          {" "}
          <TextField
            label="category"
            variant="outlined"
            fullWidth
            value={category}
            onChange={(e) => setCategory(e.target.value)}
          />
          <Button
            variant="contained"
            sx={{ width: 200, mt: 2 }}
            onClick={handleUpdate}
          >
            Update
          </Button>
        </Box>
      </Modal>
    </>
  );
};

export default DisplayCategory;
