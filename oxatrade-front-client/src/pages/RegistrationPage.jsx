import { Navigate } from "react-router-dom";
import { useAuth } from "../hooks/useAuth";

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

const Registration = () => {
  const { user } = useAuth();
  if (user) {
    // user is not authenticated
    return <Navigate to="/my" />;
  }

  return (
    <Container className="mt-4">
      <Row className="align-items-center justify-content-center">
        <Col md='12'>
          <div className="form-block mx-auto" style={{ maxWidth: 800 }}>
            <div className="text-center mb-5">
              <h1>Sign <b>Up</b></h1>
            </div>
            <Form>
              <Row>
                <Col md={6}>
                  <FormGroup floating>
                    <Input
                      id="first_name"
                      name="first_name"
                      placeholder="first name"
                    />
                    <Label for="first_name">
                      First Name
                    </Label>
                  </FormGroup>
                </Col>
                <Col md={6}>
                  <FormGroup floating>
                    <Input
                      id="last_name"
                      name="last_name"
                      placeholder="last name"
                    />
                    <Label for="last_name">
                      Last Name
                    </Label>
                  </FormGroup>
                </Col>
              </Row>
              <FormGroup floating>
                <Input
                  id="email"
                  name="email"
                  placeholder="example@email.com"
                  type="email"
                />
                <Label for="email">
                  Email
                </Label>
              </FormGroup>
              <Row>
                <Col md={6}>
                  <FormGroup floating>
                    <Input
                      id="password"
                      name="password"
                      placeholder="your password"
                      type="password"
                    />
                    <Label for="password">
                      Password
                    </Label>
                  </FormGroup>
                </Col>
                <Col md={6}>
                  <FormGroup floating>
                    <Input
                      id="repeat_password"
                      name="password"
                      placeholder="repeat your password"
                      type="password"
                    />
                    <Label for="repeat_password">
                      Repeat your password
                    </Label>
                  </FormGroup>
                </Col>
              </Row>
              <FormGroup check inline>
                <Input id="check" name="check" type="checkbox" />
                <Label check for="check">
                  I would like to receive information about news and promotions by email.
                  <br /><small className="text-muted">You can unsubscribe at any time via the newsletter or on our home page.</small>
                </Label>
              </FormGroup>
              <FormGroup className="mt-4">
                If you click <strong>“Sign in”</strong>, you accept our <strong>
                  <a rel="nofollow" target="_blank" href="/terms">terms of Use</a>
                </strong> and declare that you agree to our <strong>
                  <a rel="nofollow" target="_blank" href="/data_usage">data usage guidelines</a>
                </strong> and have read <strong><a rel="nofollow" target=" _blank" href="/data_usage#cookies">the provisions on the use of cookies</a></strong>.
              </FormGroup>
              <Button color="primary" block className="my-4">
                Sign in
              </Button>
            </Form>
          </div>
        </Col>
      </Row>
    </Container>
  )
};

export default Registration;
