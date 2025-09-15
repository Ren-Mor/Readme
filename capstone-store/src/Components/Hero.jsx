import "bootstrap/dist/css/bootstrap.min.css";
import "bootstrap-icons/font/bootstrap-icons.css";
import { Button } from "react-bootstrap";
import { NavLink } from "react-router-dom";
import "../App.css";

function Hero() {
  return (
    <section className="text-border hero-background text-white text-center d-flex flex-column justify-content-center align-items-center">
      <h2 className="text-border">Il modo smart di giocare</h2>
      <h1 className="text-border">CREA IL TUO PC PERSONALIZZATO</h1>
      <h3 className="text-border">Esperienza perfetta</h3>
      <NavLink to="/prodotti">
        <Button className="btn btn-danger mt-4 px-3 fs-4">Inizia adesso</Button>
      </NavLink>
    </section>
  );
}

export default Hero;
