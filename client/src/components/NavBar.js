import { AppBar, Toolbar, Box, Typography } from "@mui/material";
// import { VideogameAssetOutlined } from "@mui/icons-material";
import React from "react";
import { NavLink } from "react-router-dom";

const navStyles = {
  color: "inherit",
  textDecoration: "none",
};

const NavBar = () => {
  return (
    <AppBar>
      <Toolbar
        sx={{
          display: "flex",
          justifyContent: "space-between",
          alignItems: "center",
          alignContent: "center",
        }}
      >
        <Box flexGrow={1}>
          <Typography component={NavLink} to="/" sx={navStyles}>
            X STORE
          </Typography>
        </Box>
        <Box>
          <Typography component={NavLink} to="/dashboard" sx={navStyles}>
            DASHBOARD
          </Typography>
        </Box>
      </Toolbar>
    </AppBar>
  );
};

export default NavBar;
