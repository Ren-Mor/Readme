import { Container, Row, Col } from "react-bootstrap";
import { Link } from "react-router-dom";
import { useSelector } from "react-redux";
const UserProfile = function () {
  const user = useSelector((state) => state.login.user);
  if (!user) {
    return (
      <Container className="my-5 py-5 text-center">
        <h4>Devi accedere per vedere il profilo.</h4>
      </Container>
    );
  }
  return (
    <>
      <section className="position-fixed start-0 top-0 z-n1 opacity-50 hero-background text-white text-center d-flex flex-column justify-content-center align-items-center"></section>
      <Container className="my-5 py-5">
        <Row className="my-3 py-3">
          <Col xs="auto">
            <Row>
              <h4>Il tuo profilo</h4>
              <h3>
                {user.nome} {user.cognome}
              </h3>
              <h6>{user.email}</h6>
            </Row>
            <Row>
              <ul className="list-unstyled">
                <li>
                  <Link className="text-decoration-none text-black fw-bold">
                    Il tuo indirizzo
                  </Link>
                </li>
                <li>
                  <Link className="text-decoration-none text-black fw-bold">
                    Visualizza ordini
                  </Link>
                </li>
                <li>
                  <Link className="text-decoration-none text-black fw-bold">
                    Dati profilo
                  </Link>
                </li>
                <li>
                  <Link className="text-decoration-none text-black fw-bold">
                    Log out
                  </Link>
                </li>
              </ul>
            </Row>
          </Col>
          <Col></Col>
        </Row>
      </Container>
    </>
  );
};

export default UserProfile;
