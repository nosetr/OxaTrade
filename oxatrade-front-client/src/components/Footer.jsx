import { NavLink } from "react-router-dom";
import {
    Container,
    UncontrolledDropdown,
    DropdownMenu,
    DropdownItem,
    DropdownToggle,
    Button
} from "reactstrap";
import * as Icon from 'react-bootstrap-icons';
import logo from '/logo.svg';

const Footer = () => {
    return (
        <footer className="footer mt-auto py-3 bg-body-tertiary">
            <Container>
                <div className="d-flex flex-wrap justify-content-between align-items-center py-3 my-4 border-top">
                    <div className="col-md-4 d-flex align-items-center">
                        <NavLink to="/">
                            <img src={logo} alt="logo" className="position-relative img-fluid" />
                        </NavLink>
                        <span className="ms-3 mb-3 mb-md-0 text-body-secondary">&copy; 2023 Company, Inc</span>
                    </div>
                    <ul className="nav col-md-4 justify-content-end list-unstyled d-flex">
                        <li className="ms-3">
                            <a className="text-body-secondary" target="_blank" href="#">
                                <Icon.Twitter />
                            </a>
                        </li>
                        <li className="ms-3">
                            <a className="text-body-secondary" target="_blank" href="#">
                                <Icon.Instagram />
                            </a>
                        </li>
                        <li className="ms-3">
                            <a className="text-body-secondary" target="_blank" href="#">
                                <Icon.Facebook />
                            </a>
                        </li>
                        <li className="ms-3">
                            <div className="vr d-none d-lg-flex h-100 mx-lg-2 text-white"></div>
                            <hr className="d-lg-none my-2 text-white-50" />
                        </li>

                        <li>
                            <div class="dropdown">
                                <button class="btn btn-secondary dropdown-toggle" type="button" id="dropdownMenuButton1" data-bs-toggle="dropdown" aria-expanded="false">
                                    Dropdown button
                                </button>
                                <ul class="dropdown-menu" aria-labelledby="dropdownMenuButton1">
                                    <li><a class="dropdown-item" href="#">Action</a></li>
                                    <li><a class="dropdown-item" href="#">Another action</a></li>
                                    <li><a class="dropdown-item" href="#">Something else here</a></li>
                                </ul>
                            </div>
                        </li>

                        <li className="ms-3">

                            <div className="d-flex align-items-center dropdown dropup color-modes">
                                <button className="btn btn-link px-0 text-decoration-none dropdown-toggle d-flex align-items-center"
                                    id="bd-theme"
                                    type="button"
                                    aria-expanded="false"
                                    data-bs-toggle="dropdown"
                                    data-bs-display="static">
                                    <Icon.CircleHalf />
                                    {/* <svg class="bi my-1 me-2 theme-icon-active"><use href="#circle-half"></use></svg> */}
                                    <span className="ms-2" id="bd-theme-text">Toggle theme</span>
                                </button>
                                <ul className="dropdown-menu dropdown-menu-end" aria-labelledby="bd-theme">
                                    <li>
                                        <button type="button" className="dropdown-item d-flex align-items-center" data-bs-theme-value="light">
                                            <Icon.SunFill />
                                            {/* <svg class="bi me-2 opacity-50 theme-icon"><use href="#sun-fill"></use></svg> */}
                                            Light
                                            <Icon.Check2 />
                                            {/* <svg class="bi ms-auto d-none"><use href="#check2"></use></svg> */}
                                        </button>
                                    </li>
                                    <li>
                                        <button type="button" className="dropdown-item d-flex align-items-center" data-bs-theme-value="dark">
                                            <Icon.MoonStarsFill />
                                            {/* <svg class="bi me-2 opacity-50 theme-icon"><use href="#moon-stars-fill"></use></svg> */}
                                            Dark
                                            <Icon.Check2 />
                                            {/* <svg class="bi ms-auto d-none"><use href="#check2"></use></svg> */}
                                        </button>
                                    </li>
                                    <li>
                                        <button type="button" className="dropdown-item d-flex align-items-center active" data-bs-theme-value="auto">
                                            <Icon.CircleHalf />
                                            {/* <svg class="bi me-2 opacity-50 theme-icon"><use href="#circle-half"></use></svg> */}
                                            Auto
                                            <Icon.Check2 />
                                            {/* <svg class="bi ms-auto d-none"><use href="#check2"></use></svg> */}
                                        </button>
                                    </li>
                                </ul>
                            </div>








                            {/* <UncontrolledDropdown
                                className="me-2"
                                direction="up"
                            >
                                <button class=" btn-link nav-link py-2 px-0 px-lg-2 d-flex align-items-center show" data-bs-toggle="dropdown" data-bs-display="static" aria-label="Toggle theme (dark)">
                                    <svg class="bi my-1 theme-icon-active"><use href="#moon-stars-fill"></use></svg>
                                    <span class="d-lg-none ms-2" id="bd-theme-text">Toggle theme</span>
                                </button>
                                <DropdownToggle
                                    id="bd-theme"
                                    caret
                                    color="link"
                                    aria-expanded="true"
                                >
                                    Dropup
                                </DropdownToggle>
                                <DropdownMenu>
                                    <DropdownItem data-bs-theme-value="light">
                                        Light
                                    </DropdownItem>
                                    <DropdownItem data-bs-theme-value="dark">
                                        Dark
                                    </DropdownItem>
                                    <DropdownItem data-bs-theme-value="auto">
                                        Auto
                                    </DropdownItem>
                                </DropdownMenu>
                            </UncontrolledDropdown> */}
                        </li>
                    </ul>

                    {/* <ul class="navbar-nav flex-row flex-wrap ms-md-auto">




                        <li class="nav-item dropdown">
                            <button class="btn btn-link nav-link py-2 px-0 px-lg-2 dropdown-toggle d-flex align-items-center show" id="bd-theme" type="button" aria-expanded="true" data-bs-toggle="dropdown" data-bs-display="static" aria-label="Toggle theme (dark)">
                                <svg class="bi my-1 theme-icon-active"><use href="#moon-stars-fill"></use></svg>
                                <span class="d-lg-none ms-2" id="bd-theme-text">Toggle theme</span>
                            </button>
                            <ul class="dropdown-menu dropdown-menu-end show" aria-labelledby="bd-theme-text" data-bs-popper="static">
                                <li>
                                    <button type="button" class="dropdown-item d-flex align-items-center" data-bs-theme-value="light" aria-pressed="false">
                                        <svg class="bi me-2 opacity-50 theme-icon"><use href="#sun-fill"></use></svg>
                                        Light
                                        <svg class="bi ms-auto d-none"><use href="#check2"></use></svg>
                                    </button>
                                </li>
                                <li>
                                    <button type="button" class="dropdown-item d-flex align-items-center active" data-bs-theme-value="dark" aria-pressed="true">
                                        <svg class="bi me-2 opacity-50 theme-icon"><use href="#moon-stars-fill"></use></svg>
                                        Dark
                                        <svg class="bi ms-auto d-none"><use href="#check2"></use></svg>
                                    </button>
                                </li>
                                <li>
                                    <button type="button" class="dropdown-item d-flex align-items-center" data-bs-theme-value="auto" aria-pressed="false">
                                        <svg class="bi me-2 opacity-50 theme-icon"><use href="#circle-half"></use></svg>
                                        Auto
                                        <svg class="bi ms-auto d-none"><use href="#check2"></use></svg>
                                    </button>
                                </li>
                            </ul>
                        </li>

                    </ul> */}

                </div>
            </Container>
        </footer>


    )
}

export default Footer
