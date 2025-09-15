import { Navbar, Nav, Container, Form, Row, Col, Badge } from "react-bootstrap";
import { Link, NavLink, useNavigate } from "react-router-dom";
import { useState } from "react";
import { useSelector, useDispatch } from "react-redux";
import "../App.css";

function MyNav() {
  const CartContent = useSelector((state) => state.cart.content);
  const tokenStatus = useSelector((state) => state.login.token);
  const handleLogout = () => {
    dispatch({ type: "LOGOUT" });
    localStorage.removeItem("loginToken");
    localStorage.removeItem("loginUser");
  };
  const [search, setSearch] = useState("");
  const navigate = useNavigate();
  const dispatch = useDispatch();
  const handleSearchSubmit = (e) => {
    e.preventDefault();
    if (search.trim()) {
      navigate("/prodotti", { state: { search } });
    }
  };
  return (
    <Navbar
      expand="lg"
      className="bg-body-tertiary mb-3 py-4 px-5 fixed-top"
      bg="dark"
      data-bs-theme="dark"
    >
      <Container fluid>
        <Navbar.Brand href="/" className="me-5 fs-3 fw-bold">
          HW STORE
        </Navbar.Brand>
        <Navbar.Toggle aria-controls="basic-navbar-nav" />
        <Navbar.Collapse id="basic-navbar-nav">
          <Nav className="ms-5 gap-4">
            <NavLink
              to="/"
              className={({ isActive }) =>
                isActive ? "nav-link active" : "nav-link"
              }
              end
            >
              Home
            </NavLink>
            <NavLink
              to="/prodotti"
              className={({ isActive }) =>
                isActive ? "nav-link active" : "nav-link"
              }
            >
              Sfoglia prodotti
            </NavLink>
          </Nav>
          <Row className="ms-auto">
            <Col>
              <Form className="ms-auto" onSubmit={handleSearchSubmit}>
                <Form.Control
                  type="search"
                  placeholder="Cerca..."
                  className="rounded-pill"
                  aria-label="Search"
                  value={search}
                  onChange={(e) => setSearch(e.target.value)}
                />
              </Form>
            </Col>
            <Col xs="auto">
              <Nav className="d-flex flex-row gap-4 align-items-center">
                <NavLink to="/cart" className="position-relative">
                  <i className="bi bi-cart-fill fs-4"></i>
                  <Badge
                    bg="danger"
                    pill
                    className="position-absolute top-10 start-90 translate-middle "
                  >
                    {CartContent.length}
                  </Badge>
                </NavLink>
                {tokenStatus === null ? (
                  <NavLink to="/signin" className="text-decoration-none">
                    <i className="bi bi-person-circle fs-4"></i>
                    <span className="text-white access ms-2">Accedi</span>
                  </NavLink>
                ) : (
                  <Nav className="d-flex align-items-center text-decoration-none">
                    <Link to="/userprofile">
                      <i className="bi bi-person-circle fs-4"></i>
                    </Link>
                    <Link className="text-decoration-none" to="/">
                      <span
                        onClick={handleLogout}
                        className="text-white access ms-2"
                      >
                        Log out
                      </span>
                    </Link>
                  </Nav>
                )}
              </Nav>
            </Col>
          </Row>
        </Navbar.Collapse>
      </Container>
    </Navbar>
  );
}

export default MyNav;
