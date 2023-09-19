import {
  AppBar,
  Toolbar,
  Box,
  Typography,
  Button,
  Avatar,
  IconButton,
  Menu,
  MenuItem,
} from "@mui/material";
import React, { useEffect, useState } from "react";
import { NavLink } from "react-router-dom";
import { useAuth0 } from "@auth0/auth0-react";

const navStyles = {
  color: "inherit",
  textDecoration: "none",
};

const NavBar = () => {
  const [anchorElUser, setAnchorElUser] = React.useState(null);
  const [token, setToken] = useState("");
  const {
    user,
    isAuthenticated,
    loginWithRedirect,
    logout,
    getAccessTokenSilently,
  } = useAuth0();

  const handleOpenUserMenu = (event) => {
    setAnchorElUser(event.currentTarget);
  };

  const handleCloseUserMenu = () => {
    setAnchorElUser(null);
  };

  const logoutWithRedirect = () =>
    logout({
      logoutParams: {
        returnTo: window.location.origin,
      },
    });

  useEffect(() => {
    const getUserMetadata = async () => {
      try {
        const accessToken = await getAccessTokenSilently();
        setToken(accessToken);
      } catch (e) {
        console.log(e.message);
      }
    };

    getUserMetadata();
  }, [getAccessTokenSilently, user?.sub]);

  console.log("token => ", token);
  console.log("user => ", user);

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
        <Box display={"flex"} flexDirection={"row"} alignItems={"center"}>
          {!isAuthenticated && (
            <Button
              sx={{ mr: 2 }}
              color="inherit"
              onClick={() => loginWithRedirect()}
            >
              <Typography>LOGIN</Typography>
            </Button>
          )}
          {isAuthenticated && (
            <IconButton onClick={handleOpenUserMenu} sx={{ p: 0 }}>
              <Avatar alt="Profile" src={user?.picture} />
            </IconButton>
          )}
          <Menu
            sx={{ mt: "45px" }}
            id="menu-appbar"
            anchorEl={anchorElUser}
            anchorOrigin={{
              vertical: "top",
              horizontal: "right",
            }}
            keepMounted
            transformOrigin={{
              vertical: "top",
              horizontal: "right",
            }}
            open={Boolean(anchorElUser)}
            onClose={handleCloseUserMenu}
          >
            <MenuItem>
              <Typography
                textAlign="center"
                component={NavLink}
                to="/dashboard"
                sx={navStyles}
              >
                Dashboard
              </Typography>
            </MenuItem>
            <MenuItem onClick={() => logoutWithRedirect()}>
              <Typography textAlign="center">Logout</Typography>
            </MenuItem>
          </Menu>
        </Box>
      </Toolbar>
    </AppBar>
  );
};

export default NavBar;
