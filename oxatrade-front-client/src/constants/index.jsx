//const keys = require("../config/keys.js");

// export const API_BASE_URL = keys.apiBaseUrl;
export const API_BASE_URL = "http://localhost:8080";
// export const OAUTH2_REDIRECT_URI = keys.redirectUri;
export const OAUTH2_REDIRECT_URI = "http://localhost:5173/oauth2/redirect";
export const ACCESS_TOKEN = "accessToken";
export const GOOGLE_AUTH_URL = API_BASE_URL + "/oauth2/authorize/google?redirect_uri=" + OAUTH2_REDIRECT_URI;