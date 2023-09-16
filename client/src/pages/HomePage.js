import React, { useEffect, useState } from "react";
import api from "../Utils/api";

import {
  Container,
  Grid,
  Card,
  CardMedia,
  CardContent,
  Typography,
} from "@mui/material";

const HomePage = () => {
  const [products, setProducts] = useState([]);

  useEffect(() => {
    api.get(`product`).then(({ data }) => setProducts(data));
  });

  return (
    <Container sx={{ mt: 10 }}>
      <Grid container spacing={2}>
        {products.map((item) => (
          <Grid item xs={4} key={item.id}>
            <Card sx={{ height: "100%" }}>
              <CardMedia
                component="img"
                image={item.photo}
                sx={{ height: 200, margin: 1 }}
              />
              <CardContent>
                <Typography
                  gutterBottom
                  variant="h7"
                  component="div"
                ></Typography>
              </CardContent>
            </Card>
          </Grid>
        ))}
      </Grid>
    </Container>
  );
};

export default HomePage;
