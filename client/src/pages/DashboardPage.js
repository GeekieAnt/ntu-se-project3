import { useState } from "react";
import PropTypes from "prop-types";
import { Container, Box, Tabs, Tab, Typography } from "@mui/material";
import AddCateogory from "../components/AddCateogory";
import AddProduct from "../components/AddProduct";
import ProductTable from "../components/ProductTable";

function CustomTabPanel(props) {
  const { children, value, index, ...other } = props;

  return (
    <div
      role="tabpanel"
      hidden={value !== index}
      id={`simple-tabpanel-${index}`}
      aria-labelledby={`simple-tab-${index}`}
      {...other}
    >
      {value === index && (
        <Box sx={{ p: 3 }}>
          <Typography component="div">{children}</Typography>
        </Box>
      )}
    </div>
  );
}

CustomTabPanel.propTypes = {
  children: PropTypes.node,
  index: PropTypes.number.isRequired,
  value: PropTypes.number.isRequired,
};

function a11yProps(index) {
  return {
    id: `simple-tab-${index}`,
    "aria-controls": `simple-tabpanel-${index}`,
  };
}

export default function DashboardPage() {
  const [value, setValue] = useState(0);

  const handleChange = (event, newValue) => {
    setValue(newValue);
  };

  return (
    <Container sx={{ mt: 10 }}>
      <Box sx={{ borderBottom: 1, borderColor: "divider" }}>
        <Tabs value={value} onChange={handleChange}>
          <Tab label="Products" {...a11yProps(0)} />
          <Tab label="Add Product" {...a11yProps(1)} />
          <Tab label="Add Category" {...a11yProps(2)} />
        </Tabs>
      </Box>
      <CustomTabPanel value={value} index={0}>
        <ProductTable />
      </CustomTabPanel>
      <CustomTabPanel value={value} index={1}>
        <AddProduct />
      </CustomTabPanel>
      <CustomTabPanel value={value} index={2}>
        <AddCateogory />
      </CustomTabPanel>
    </Container>
  );
}
