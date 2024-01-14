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

const PassReset = () => {
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
        password: Yup.string()
            .min(8, ({ min }) => `Password must be at least ${min} characters`)
            .max(100, ({ max }) => `Password must be at most ${max} characters`)
            .required('Password is required')
            .matches(/^\S*$/, "White Spaces are not allowed")
            .matches(/\w*[a-z]\w*/, "Password must have a small letter")
            .matches(/\w*[A-Z]\w*/, "Password must have a capital letter")
            .matches(/\d/, "Password must have a number")
            .matches(/[!"#$%&'()*+,-./:;<=>?@[\]^_`{|}~]/, "Password must have a special character"),
        confirm_password: Yup.string()
            .required('Confirm Password is required')
            .oneOf([Yup.ref('password'), null], 'Passwords must match'),
    })

    const formik = useFormik({
        initialValues: {
            password: '',
            confirm_password: ''
        },
        validationSchema,
        onSubmit: (values, { setSubmitting, setFieldError }) => {
            setLoading(true)

            axios.post(API_AUTH_URL + '/api/v1/auth/pass_reset', values)
                .then(e => {
                    setLoading(false)
                    setSubmitting(false)
                })
                .catch(err => {
                    setLoading(false)
                    setSubmitting(false)
                    let erMsg
                    if (err.response) {
                        erMsg = JSON.stringify(err.response.data.errors[0].message)
                        setFieldError('password', erMsg)
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
                                Please enter your new password and confirm it.
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

export default PassReset;