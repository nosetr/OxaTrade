import { GoogleLogin } from "react-google-login";
// React Router v6 introduces a new navigation API that is synonymous with <Link>
// and provides better compatibility with suspense-enabled apps:
import { useNavigate } from "react-router-dom";

export default () => {
  let history = useNavigate();

  const handleGoogleLoginSuccess = (response) => {
    // Send the access token to the backend for authentication
    // On successful authentication, redirect to the main application page
    history.push("/dashboard");
  };
  const handleGoogleLoginFailure = (error) => {
    console.log("Google login failed:", error);
  };

  return (
    <>
      <h1>Login</h1>
      <GoogleLogin
        clientId="YOUR_GOOGLE_CLIENT_ID"
        buttonText="Login with Google"
        onSuccess={handleGoogleLoginSuccess}
        onFailure={handleGoogleLoginFailure}
        cookiePolicy="single_host_origin"
      />
    </>
  );
};
