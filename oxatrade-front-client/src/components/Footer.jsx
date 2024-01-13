import { NavLink } from "react-router-dom";
import { Container } from "reactstrap";
import * as Icon from 'react-bootstrap-icons';
import logo from '/logo.svg';
import ThemeSwitcher from '../components/ThemeSwitcher'

const Footer = () => {

    return (
        <footer className="footer mt-auto py-3 bd-navbar bg-body-tertiary">
            <Container>
                <div className="d-flex flex-wrap justify-content-between align-items-center py-3 my-4 border-top">
                    <div className="col-md-4 d-flex align-items-center">
                        <NavLink to="/">
                            <img src={logo} alt="logo" className="position-relative img-fluid" />
                        </NavLink>
                        <span className="ms-3 mb-3 mb-md-0 text-body-secondary">&copy; 2023 Company, Inc</span>
                    </div>
                    <ul className="nav col-md-4 justify-content-end list-unstyled d-flex">
                        <li className="ms-3 d-flex align-items-center">
                            <a className="text-body-secondary d-flex align-items-center" target="_blank" href="#">
                                <Icon.Twitter />
                            </a>
                        </li>
                        <li className="ms-3 d-flex align-items-center">
                            <a className="text-body-secondary d-flex align-items-center" target="_blank" href="#">
                                <Icon.Instagram />
                            </a>
                        </li>
                        <li className="ms-3 d-flex align-items-center">
                            <a className="text-body-secondary d-flex align-items-center" target="_blank" href="#">
                                <Icon.Facebook />
                            </a>
                        </li>
                        <li className="ms-3">
                            <div className="vr d-none d-lg-flex h-100 mx-lg-2 text-white"></div>
                            <hr className="d-lg-none my-2 text-white-50" />
                        </li>

                        <li className="ms-3">
                            <ThemeSwitcher />
                        </li>
                    </ul>
                </div>
            </Container>
        </footer>
    )
}

export default Footer
