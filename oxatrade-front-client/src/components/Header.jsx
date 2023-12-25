// The <Outlet> renders the current route selected.
// <Link> is used to set the URL and keep track of browsing history.
// import { Outlet, Link } from "react-router-dom";
import { NavLink as RRNavLink } from "react-router-dom";

import logo from '/logo.svg';
import {
  Container, Row, Col, Form, InputGroup, Input, Button, Navbar, Nav,
  NavbarBrand, NavLink, NavItem, UncontrolledDropdown,
  DropdownToggle, DropdownMenu, DropdownItem
} from 'reactstrap';

const AVATAR = 'https://www.gravatar.com/avatar/429e504af19fc3e1cfa5c4326ef3394c?s=240&d=mm&r=pg';

const Header = () => (
  <>
    <header>
      <Navbar full="true" container="fluid" expand="md" className="border-bottom" style={{ height: 80 }}>

        <Container>
          <Row g-0 className="position-relative w-100 align-items-center">

            <Col className="d-none d-lg-flex justify-content-start">
              <Nav className="mrx-auto" navbar>

                <NavItem className="d-flex align-items-center">
                  <NavLink className="font-weight-bold" href="/">
                    <img src={AVATAR} alt="avatar" className="img-fluid rounded-circle" style={{ width: 36 }} />
                  </NavLink>
                </NavItem>

                <NavItem className="d-flex align-items-center">
                  <NavLink className="font-weight-bold" to="/" exact tag={RRNavLink}>Home</NavLink>
                </NavItem>

                <NavItem className="d-flex align-items-center">
                  <NavLink className="font-weight-bold" to="/login" tag={RRNavLink}>Login</NavLink>
                </NavItem>

                <UncontrolledDropdown className="d-flex align-items-center" nav inNavbar>
                  <DropdownToggle className="font-weight-bold" nav caret>Learn</DropdownToggle>
                  <DropdownMenu end>
                    <DropdownItem className="font-weight-bold text-secondary text-uppercase" header disabled>Learn React</DropdownItem>
                    <DropdownItem divider />
                    <DropdownItem>
                      <RRNavLink to="/login">Login</RRNavLink>
                    </DropdownItem>
                    <DropdownItem>
                      <RRNavLink to="/contact">Contact</RRNavLink>
                    </DropdownItem>
                    <DropdownItem>
                      <RRNavLink to="/add_address">Add Address</RRNavLink>
                    </DropdownItem>
                  </DropdownMenu>
                </UncontrolledDropdown>
                {/* <Outlet /> */}
              </Nav>
            </Col>

            <Col className="d-flex justify-content-xs-start justify-content-lg-center">
              <NavbarBrand className="d-inline-block p-0" href="/" style={{ width: 80 }}>
                <img src={logo} alt="logo" className="position-relative img-fluid" />
              </NavbarBrand>
            </Col>

            <Col className="d-none d-lg-flex justify-content-end">
              <Form inline>
                <InputGroup>
                  <Input type="search" className="mr-3" placeholder="Search React Courses" />
                  <Button type="submit" color='primary'>
                    Search
                  </Button>
                </InputGroup>
              </Form>
            </Col>

          </Row>
        </Container>

      </Navbar>
    </header>
  </>
);

export default Header;