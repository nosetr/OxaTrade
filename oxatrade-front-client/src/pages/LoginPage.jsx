import './LoginPage.css'

import { GoogleLogin } from "react-google-login";
// React Router v6 introduces a new navigation API that is synonymous with <Link>
// and provides better compatibility with suspense-enabled apps:
import { NavLink, Form as DomForm, Navigate, useNavigate } from "react-router-dom";
import { useAuth } from "../hooks/useAuth";
import * as Icon from 'react-bootstrap-icons'

import {
  Button,
  Col,
  Container,
  Row,
  Form,
  FormGroup,
  Input,
  Label,

} from "reactstrap";

const LoginPage = () => {
  return (
    <Container className="mt-4">
      <Row className="align-items-center justify-content-center">
        <Col md='12'>
          <div className="mx-auto" style={{maxWidth: 500}}>
            <div className="text-center mb-5">
              <h1>Log<b>In</b></h1>
            </div>
            <div>
              <Button block href="#" tag="a" className="btn-facebook">
                <Icon.Facebook className="me-3" size={30} />Login with facebook
              </Button>
              <Button block href="#" tag="a" className="btn-google mt-2" >
                <Icon.Google className='me-3' size={30} />Login with Google
              </Button>
            </div>
            <span className="text-center my-3 d-block">or</span>
            <Form>
              <FormGroup floating>
                <Input id="exampleEmail" name="email" placeholder="Email" type="email" />
                <Label for="exampleEmail">Email</Label>
              </FormGroup>

              <FormGroup floating>
                <Input id="examplePassword" name="password" placeholder="Password" type="password" />
                <Label for="examplePassword">Password</Label>
              </FormGroup>

              <FormGroup check inline>
                <Input id='remember_me' name='remember_me' type="checkbox" />
                <Label for='remember_me' check>
                  Keep me logged in
                </Label>
              </FormGroup>

              <Button color="primary" block className="mt-4">
                Login In
              </Button>
            </Form>
            <hr className="mt-5 mb-4 border-secondary-subtle" />

            <div className='position-relative align-items-center row'>
              <div className='d-none d-lg-flex justify-content-start col'>
                <NavLink className="link-secondary text-decoration-none" to="/signup">
                  Create new account
                </NavLink>
              </div>
              <div className='d-none d-lg-flex justify-content-end col'>
                <a href="#!" className="link-secondary text-decoration-none">Forgot password</a>
              </div>
            </div>

          </div>
        </Col>
      </Row>
    </Container>
  )
}

export default LoginPage

// export default () => {

//   // const { user } = useAuth();
//   // if (user) {
//   //   // user is not authenticated
//   //   return <Navigate to="/my" />;
//   // }

//   let history = useNavigate();

//   const responseGoogle = (response) => {
//     console.log(response);
//   }

//   const handleGoogleLoginSuccess = (response) => {
//     // Send the access token to the backend for authentication
//     // On successful authentication, redirect to the main application page
//     history.push("/dashboard");
//   };
//   const handleGoogleLoginFailure = (error) => {
//     console.log("Google login failed:", error);
//   };

//   return (
//     <>
//       <h1>Login</h1>
//       <GoogleLogin
//         clientId="1061580659113-kdja71abupqctcd47m8iv9iqjii3knfq.apps.googleusercontent.com"
//         buttonText="Login with Google"
//         onSuccess={responseGoogle}
//         onFailure={responseGoogle}
//         // onSuccess={handleGoogleLoginSuccess}
//         // onFailure={handleGoogleLoginFailure}
//         cookiePolicy="single_host_origin"
//       />
//     </>
//   );
// };
