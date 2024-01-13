import { useState } from 'react'
import axios from 'axios'
import { NavLink, Navigate } from 'react-router-dom'
import { toast } from 'react-toastify'

import { useAuth } from '../../../hooks/useAuth'
import { API_AUTH_URL } from '../../../constants'

import {
  Button,
  Col,
  Container,
  Row,
  Form,
  FormGroup,
  Input,
  Label,
  FormFeedback
} from 'reactstrap'
import * as Yup from 'yup'
import { useFormik } from 'formik'

const Registration = () => {
  const { user } = useAuth();
  if (user) {
    // user is not authenticated
    return <Navigate to="/my" />;
  }

  const [loading, setLoading] = useState(false)

  const SpinnerBorder = () => (
    <span class="spinner-border spinner-border-sm me-2" role="status" aria-hidden="true"></span>
  )

  const validationSchema = Yup.object().shape({
    first_name: Yup.string()
      .min(2, 'First name must be at least 2 characters')
      .max(64, 'First name must be at most 64 characters')
      .required('First name is required'),
    last_name: Yup.string()
      .min(2, 'Last name must be at least 2 characters')
      .max(64, 'Last name must be at most 64 characters')
      .required('Last name is required'),
    email: Yup.string().email('Invalid email').required('Email is required'),
    password: Yup.string()
      .min(8, 'Password must be at least 8 characters')
      .max(100, 'Password must be at most 100 characters')
      .required('Password is required'),
    confirm_password: Yup.string()
      .required('Confirm Password is required')
      .oneOf([Yup.ref('password'), null], 'Passwords must match'),
  })

  const formik = useFormik({
    initialValues: {
      first_name: '',
      last_name: '',
      email: '',
      password: '',
      confirm_password: ''
    },
    validationSchema,
    onSubmit: (values, { setSubmitting, setFieldError }) => {
      setLoading(true)
      console.log(values)
      axios.post(API_AUTH_URL + '/api/v1/auth/register', values)
        .then(e => {
          console.log(e.data)
          setLoading(false)
          setSubmitting(false)
        })
        .catch(err => {
          setLoading(false)
          setSubmitting(false)
          let erMsg
          if (err.response) {
            erMsg = JSON.stringify(err.response.data.errors[0].message)
            setFieldError('first_name', erMsg)
          } else if (err.request) {
            erMsg = 'Network Error:', err.request
          } else {
            erMsg = 'Error:', err.message
          }
          toast.error(`${erMsg}`, {
            position: "bottom-right",
            pauseOnHover: true,
            draggable: true
          })
        })
    }
  })

  return (
    <Container className="mt-4">
      <Row className="align-items-center justify-content-center">
        <Col md='12'>
          <div className="form-block mx-auto" style={{ maxWidth: 800 }}>
            <div className="text-center mb-5">
              <h1>Sign <b>Up</b></h1>
            </div>
            <Form onSubmit={formik.handleSubmit}>
              <Row>
                <Col md={6}>
                  <FormGroup floating>
                    <Input
                      id="first_name"
                      name="first_name"
                      placeholder="first name"
                      onChange={formik.handleChange}
                      onBlur={formik.handleBlur}
                      invalid={formik.touched.first_name && !!formik.errors.first_name}
                    />
                    <Label for="first_name">
                      First Name
                    </Label>
                    <FormFeedback>{formik.errors.first_name}</FormFeedback>
                  </FormGroup>
                </Col>
                <Col md={6}>
                  <FormGroup floating>
                    <Input
                      id="last_name"
                      name="last_name"
                      placeholder="last name"
                      onChange={formik.handleChange}
                      onBlur={formik.handleBlur}
                      invalid={formik.touched.last_name && !!formik.errors.last_name}
                    />
                    <Label for="last_name">
                      Last Name
                    </Label>
                    <FormFeedback>{formik.errors.last_name}</FormFeedback>
                  </FormGroup>
                </Col>
              </Row>
              <FormGroup floating>
                <Input
                  id="email"
                  name="email"
                  placeholder="example@email.com"
                  type="email"
                  onChange={formik.handleChange}
                  onBlur={formik.handleBlur}
                  invalid={formik.touched.email && !!formik.errors.email}
                />
                <Label for="email">
                  Email
                </Label>
                <FormFeedback>{formik.errors.email}</FormFeedback>
              </FormGroup>
              <Row>
                <Col md={6}>
                  <FormGroup floating>
                    <Input
                      id="password"
                      name="password"
                      placeholder="your password"
                      type="password"
                      onChange={formik.handleChange}
                      onBlur={formik.handleBlur}
                      invalid={formik.touched.password && !!formik.errors.password}
                    />
                    <Label for="password">
                      Password
                    </Label>
                    <FormFeedback>{formik.errors.password}</FormFeedback>
                  </FormGroup>
                </Col>
                <Col md={6}>
                  <FormGroup floating>
                    <Input
                      id="confirm_password"
                      name="confirm_password"
                      placeholder="repeat your password"
                      type="password"
                      onChange={formik.handleChange}
                      onBlur={formik.handleBlur}
                      invalid={formik.touched.confirm_password && !!formik.errors.confirm_password}
                    />
                    <Label for="confirm_password">
                      Repeat your password
                    </Label>
                    <FormFeedback>{formik.errors.confirm_password}</FormFeedback>
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
              <Button color="primary" block className="my-4"
                disabled={formik.isSubmitting}>
                {loading && <SpinnerBorder />}
                Sign in
              </Button>
            </Form>

            <hr className="mt-5 mb-4 border-secondary-subtle" />

            <div className='position-relative align-items-center row'>
              <div className='d-none d-lg-flex justify-content-start col'>
                <NavLink className="link-secondary text-decoration-none" to="/login">
                  Login In
                </NavLink>
              </div>
            </div>

          </div>
        </Col>
      </Row>
    </Container>
  )
};

export default Registration;
