import { NavLink, Outlet, useNavigate } from "react-router-dom";
import Footer from "../components/Footer";
import logo from '/logo.svg';
import {
    Container,
    Row,
    Col,
    Button,
    Navbar,
    NavbarBrand
} from 'reactstrap';

const EmptyLayout = () => {
    const navigate = useNavigate();
    return (
        <>
            <header>
                <Navbar full="true" container="fluid" expand="md" className="py-3 mb-3 border-bottom">

                    <Container>
                        <Row g-0 className="position-relative align-items-center">

                            <Col className="d-none d-lg-flex justify-content-start">
                                <NavbarBrand className="d-inline-block p-0" style={{ width: 80 }}>
                                    <NavLink to="/">
                                        <img src={logo} alt="logo" className="position-relative img-fluid" />
                                    </NavLink>
                                </NavbarBrand>
                            </Col>

                            <Col className="d-none d-lg-flex justify-content-end">
                                <Button
                                    close
                                    size="lg" onClick={() => navigate(-1)} />
                            </Col>

                        </Row>
                    </Container>

                </Navbar>
            </header>
            <main className="mb-3 w-100">
                <Outlet />
            </main>
            <Footer />
        </>
    )
}

export default EmptyLayout