import './LoginPage.css'
import React, { useState } from 'react'
import axios from 'axios'
import { API_AUTH_URL, GOOGLE_AUTH_URL, FACEBOOK_AUTH_URL } from '../../../constants'

import { GoogleLogin } from "react-google-login";
// React Router v6 introduces a new navigation API that is synonymous with <Link>
// and provides better compatibility with suspense-enabled apps:
import { NavLink, Navigate } from 'react-router-dom'
import { useAuth } from '../../../hooks/useAuth'
import * as Icon from 'react-bootstrap-icons'
import { toast } from 'react-toastify'

import {
  Button,
  Col,
  Container,
  Row,
  Form,
  FormGroup,
  FormFeedback,
  Input,
  Label,
  InputGroup
} from 'reactstrap'
import * as Yup from 'yup'
import { useFormik } from 'formik'

const LoginPage = () => {
  const { user } = useAuth();
  if (user) {
    // user is authenticated
    return <Navigate to="/my" />
  }

  const [checked, setChecked] = useState(false)
  const [loading, setLoading] = useState(false)
  const [showPassword, setShowPassword] = useState(false)

  // const PreLoader = () => (
  //   <div id="n2s-preloader">
  //     <div class="n2s-preloader-bg"></div>
  //     <div class="n2s-loader">
  //       <div></div>
  //       <div></div>
  //       <div></div>
  //     </div>
  //   </div>
  // )

  const SpinnerBorder = () => (
    <span class="spinner-border spinner-border-sm me-2" role="status" aria-hidden="true"></span>
  )

  const validationSchema = Yup.object().shape({
    email: Yup.string().email('Invalid email').required('Email is required'),
    password: Yup.string()
      .min(8, 'Password must be at least 8 characters')
      .max(100, 'Password must be at most 100 characters')
      .required('Password is required'),
  })

  const formik = useFormik({
    initialValues: {
      email: '',
      password: ''
    },
    validationSchema,
    onSubmit: (values, { setSubmitting, setFieldError }) => {
      setLoading(true)

      axios.post(API_AUTH_URL + '/api/v1/auth/login', values)
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
            erMsg = err.response.data.errors[0].message
            setFieldError('email', erMsg)
          } else {
            erMsg = 'An error has occurred. Please try again later.'
          }
          // https://deadsimplechat.com/blog/react-toastify-the-complete-guide/
          toast.error(`${erMsg}`, {
            position: "bottom-right",
            // autoClose: false,
            // hideProgressBar: false,
            // closeOnClick: false,
            pauseOnHover: true,
            draggable: true,
            // progress: 0,
          })
        })
    }
  })

  return (
    <Container className="mt-4">
      <Row className="align-items-center justify-content-center">
        <Col md='12'>
          <div className="mx-auto" style={{ maxWidth: 500 }}>
            <div className="text-center mb-5">
              <h1>Log<b>In</b></h1>
            </div>
            <div>
              <Button block href={GOOGLE_AUTH_URL} tag="a" className="btn-google"
                disabled={formik.isSubmitting}>
                <Icon.Google className='me-3' size={30} />Login with Google
              </Button>
              <Button block href={FACEBOOK_AUTH_URL} tag="a" className="btn-facebook mt-2"
                disabled={formik.isSubmitting}>
                <Icon.Facebook className="me-3" size={30} />Login with facebook
              </Button>
            </div>
            <span className="text-center my-3 d-block">or</span>
            <Form onSubmit={formik.handleSubmit}>
              <FormGroup floating>
                <Input id="email" name="email" placeholder="Email" type="email"
                  onChange={formik.handleChange}
                  onBlur={formik.handleBlur}
                  invalid={formik.touched.email && !!formik.errors.email} />
                <Label for="email">Email</Label>
                <FormFeedback>{formik.errors.email}</FormFeedback>
              </FormGroup>
              <InputGroup className='mb-5'>
                <div className="form-floating">
                  <Input id="password" name="password" placeholder="Password"
                    type={
                      showPassword ? "text" : "password"
                    }
                    onChange={formik.handleChange}
                    onBlur={formik.handleBlur}
                    invalid={formik.touched.password && !!formik.errors.password} />
                  <Label for="password">Password</Label>
                  <FormFeedback>{formik.errors.password}</FormFeedback>
                </div>
                <Button
                  onClick={() =>
                    setShowPassword((prev) => !prev)
                  } >
                  <Icon.Eye size={25} />
                </Button>
              </InputGroup>
              <FormGroup check inline>
                <Input id="remember_me" name="remember_me" type="checkbox"
                  onChange={() => { setChecked(!checked) }} />
                <Label for="remember_me" check>
                  Keep me logged in
                </Label>
              </FormGroup>

              <Button color="primary" block className="mt-4"
                disabled={formik.isSubmitting}>
                {loading && <SpinnerBorder />}
                Log In
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
                <NavLink className="link-secondary text-decoration-none" to="/pass_request">
                  Forgot password
                </NavLink>
              </div>
            </div>

          </div>
        </Col>
      </Row>
      {/* {loading && <PreLoader />} */}
    </Container>
  )
}

export default LoginPage
