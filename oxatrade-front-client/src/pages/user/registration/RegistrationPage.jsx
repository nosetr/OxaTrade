import { useState } from 'react'
import axios from 'axios'
import { NavLink, Navigate } from 'react-router-dom'
import { toast } from 'react-toastify'

import { useAuth } from '../../../hooks/useAuth'
import { API_AUTH_URL, TITLES } from '../../../constants'

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
import Select from 'react-select'

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
    title: Yup.object().required('Title is required'),
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
      .min(8, ({ min }) => `Password must be at least ${min} characters`)
      .max(100, ({ max }) => `Password must be at most ${max} characters`)
      .required('Password is required')
      .matches(/^\S*$/, "White Spaces are not allowed")
      .matches(/\w*[a-z]\w*/,  "Password must have a small letter")
      .matches(/\w*[A-Z]\w*/,  "Password must have a capital letter")
      .matches(/\d/, "Password must have a number")
      .matches(/[!"#$%&'()*+,-./:;<=>?@[\]^_`{|}~]/, "Password must have a special character"),
      // .matches(/^(?=.*[a-z])(?=.*[A-Z])(?=.*\d)(?=.*[!"#$%&'()*+,-./:;<=>?@[\]^_`{|}~])[A-Za-z\d!"#$%&'()*+,-./:;<=>?@[\]^_`{|}~]{8,}$/,
        // "Must Contain 8 Characters, One Uppercase, One Lowercase, One Number and one special case Character"),
    confirm_password: Yup.string()
      .required('Confirm Password is required')
      .oneOf([Yup.ref('password'), null], 'Passwords must match'),
  })

  const formik = useFormik({
    initialValues: {
      title: '',
      first_name: '',
      last_name: '',
      email: '',
      password: '',
      confirm_password: '',
      newsletter: false
    },
    validationSchema,
    onSubmit: (values, { setSubmitting, setFieldError }) => {
      setLoading(true)
      // Convert Title from object to string:
      for (const [key, val] of Object.entries(values)) {
        if ((val instanceof Object))
          values[key] = values[key].value
      }

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
              <h1>Sign <b>Up</b></h1>
            </div>
            <Form onSubmit={formik.handleSubmit}>
              <FormGroup row>
                <Select
                  classNamePrefix="react-select-lg"
                  indicatorSeparator={null}
                  options={TITLES}
                  getOptionValue={option => option.value}
                  getOptionLabel={option => option.label}
                  value={formik.values.title}
                  onChange={(title) => {
                    formik.setFieldValue('title', title);
                  }}
                  placeholder="Select title..."
                  className={(formik.touched.title && !!formik.errors.title) && 'n2s-select-is-invalid'}
                  styles={{
                    control: (provided, state) => ({
                      ...provided,
                      boxShadow: state.isFocused ? "0 0 0 .25rem rgba(13,110,253,.25)" : "none",
                      border: state.isFocused ? "var(--bs-border-width) solid #86b7fe" : "var(--bs-border-width) solid var(--bs-border-color)",
                      '&:hover': {
                        borderColor: 'none'
                      },
                      backgroundColor: "var(--bs-body-bg)",
                      borderRadius: "var(--bs-border-radius)",
                      color: "var(--bs-body-color)",
                      width: "100%"
                    }),
                    valueContainer: (provided, state) => ({
                      ...provided,
                      padding: ".375rem .75rem"
                    }),
                    singleValue: (provided, state) => ({
                      ...provided,
                      color: "var(--bs-body-color)"
                    }),
                    menu: (provided, state) => ({
                      ...provided,
                      zIndex: '3'
                    }),
                    menuList: (provided, state) => ({
                      ...provided,
                      backgroundColor: "var(--bs-body-bg)"
                    }),
                    option: (provided, state) => ({
                      ...provided,
                      backgroundColor: state.isSelected ? '#00AEEC' : '#var(--bs-body-bg)',
                      "&:hover": {
                        backgroundColor: '#00AEEC',
                        color: "#dee2e6"
                      }
                    }),
                    dropdownIndicator: (provided, state) => ({
                      ...provided,
                      '&:hover': {
                        color: 'inherit'
                      }
                    }),
                  }}
                />
                {(!!formik.errors.title && formik.touched.title) && (
                  <FormFeedback className="d-block">{formik.errors.title}</FormFeedback>
                )}
              </FormGroup>
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
                <Input id="check" name="check" type="checkbox"
                  onChange={() => {
                    formik.setFieldValue('newsletter', !formik.values.newsletter)
                  }} />
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
