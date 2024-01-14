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

const PassRequest = () => {
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
    email: Yup.string().email('Invalid email').required('Email is required'),
  })

  const formik = useFormik({
    initialValues: {
      email: ''
    },
    validationSchema,
    onSubmit: (values, { setSubmitting, setFieldError }) => {
      setLoading(true)

      axios.post(API_AUTH_URL + '/api/v1/auth/pass_request', values)
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
            setFieldError('email', erMsg)
          } else {
            erMsg = 'An error has occurred. Please try again later.'
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
              <h1>Reset your <b>password</b></h1>
            </div>
            <Form onSubmit={formik.handleSubmit}>
              <FormGroup className="mt-4">
                Please enter your email address. We will send you an email to reset your password. Please note that if you signed up using Google or Facebook this form will not work.
              </FormGroup>
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
              <Button color="primary" block className="my-4"
                disabled={formik.isSubmitting}>
                {loading && <SpinnerBorder />}
                Send
              </Button>
            </Form>

            <hr className="mt-5 mb-4 border-secondary-subtle" />

            <div className='position-relative align-items-center row'>
              <div className='d-none d-lg-flex justify-content-start col'>
                <NavLink className="link-secondary text-decoration-none" to="/login">
                  Login In
                </NavLink>
              </div>
              <div className='d-none d-lg-flex justify-content-end col'>
                <NavLink className="link-secondary text-decoration-none" to="/signup">
                  Create new account
                </NavLink>
              </div>
            </div>

          </div>
        </Col>
      </Row>
    </Container>
  )
}

export default PassRequest;