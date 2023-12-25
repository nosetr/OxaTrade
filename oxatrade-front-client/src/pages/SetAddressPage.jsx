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

const Address = () => {
  const { user } = useAuth();
  if (user) {
    // user is not authenticated
    return <Navigate to="/my" />;
  }

  return (
    <Container className="mt-4">
      <Row className="align-items-center justify-content-center">
        <Col md='12'>
          <div className="form-block mx-auto" style={{maxWidth: 800}}>
            <div className="text-center mb-5">
              <h1>Contact <b>Details</b></h1>
            </div>
            <Form>

              <FormGroup floating>
                <Input
                  id="exampleAddress"
                  name="address"
                  placeholder="1234 Main St"
                />
                <Label for="exampleAddress">
                  Name / Firma/ Organisation
                </Label>
              </FormGroup>

              <Row>
                <Col md={10}>
                  <FormGroup floating>
                    <Input
                      id="exampleEmail"
                      name="email"
                      placeholder="with a placeholder"
                      type="email"
                    />
                    <Label for="exampleEmail">
                      Street
                    </Label>
                  </FormGroup>
                </Col>
                <Col md={2}>
                  <FormGroup floating>
                    <Input
                      id="examplePassword"
                      name="password"
                      placeholder="password placeholder"
                      type="password"
                    />
                    <Label for="examplePassword">
                      Nr.
                    </Label>
                  </FormGroup>
                </Col>
              </Row>

              <FormGroup floating>
                <Input
                  id="exampleAddress2"
                  name="address2"
                  placeholder="Apartment, studio, or floor"
                />
                <Label for="exampleAddress2">
                  Additional Information
                </Label>
              </FormGroup>

              <Row>
                <Col md={6}>
                  <FormGroup floating>
                    <Input
                      id="exampleEmail"
                      name="email"
                      placeholder="with a placeholder"
                      type="email"
                    />
                    <Label for="exampleEmail">
                      City
                    </Label>
                  </FormGroup>
                </Col>
                <Col md={4}>
                  <FormGroup floating>
                    <Input
                      id="examplePassword"
                      name="password"
                      placeholder="password placeholder"
                      type="password"
                    />
                    <Label for="examplePassword">
                      Country
                    </Label>
                  </FormGroup>
                </Col>
                <Col md={2}>
                  <FormGroup floating>
                    <Input
                      id="examplePassword"
                      name="password"
                      placeholder="password placeholder"
                      type="password"
                    />
                    <Label for="examplePassword">
                      Zip
                    </Label>
                  </FormGroup>
                </Col>
              </Row>

              <Row>
                <Col md={2}>
                  <FormGroup floating>
                    <Input
                      id="exampleEmail"
                      name="email"
                      placeholder="with a placeholder"
                      type="email"
                    />
                    <Label for="exampleEmail">
                      Code +
                    </Label>
                  </FormGroup>
                </Col>
                <Col md={10}>
                  <FormGroup floating>
                    <Input
                      id="examplePassword"
                      name="password"
                      placeholder="password placeholder"
                      type="password"
                    />
                    <Label for="examplePassword">
                      Phone Number
                    </Label>
                  </FormGroup>
                </Col>
              </Row>

              <Button color="primary" block className="my-4">
                Save
              </Button>
            </Form>
          </div>
        </Col>
      </Row>
    </Container>
  )
};

export default Address;
