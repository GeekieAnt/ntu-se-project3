import React from "react";
import ReactDOM from "react-dom/client";
import App from "./App";
import "@fontsource/roboto/300.css";
import "@fontsource/roboto/400.css";
import "@fontsource/roboto/500.css";
import "@fontsource/roboto/700.css";
import { Auth0Provider } from "@auth0/auth0-react";

const root = ReactDOM.createRoot(document.getElementById("root"));
root.render(
  <Auth0Provider
    domain="dev-zldrlxetiu43era6.us.auth0.com"
    clientId="31bbG0zb3thSSLtfXEQiq7BPac6bmC4i"
    authorizationParams={{
      redirect_uri: window.location.origin,
      audience: "https://se-project-api/",
      scope: "read:current_user update:current_user_metadata",
    }}
  >
    <React.StrictMode>
      <App />
    </React.StrictMode>
  </Auth0Provider>
);
