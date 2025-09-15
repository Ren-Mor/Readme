import "bootstrap/dist/css/bootstrap.min.css";
import "bootstrap-icons/font/bootstrap-icons.css";
import { useLocation } from "react-router-dom";
import { useDispatch } from "react-redux";
import { Container, Card, Button } from "react-bootstrap";
import MyNav from "./MyNav";
import MyFooter from "./MyFooter";
import "../App.css";

function ProductDetails() {
  const dispatch = useDispatch();
  const addToCartRedux = () => {
    dispatch({ type: "ADD_TO_CART", payload: data });
  };
  const location = useLocation();
  const { data } = location.state;
  return (
    <>
      <section className="position-fixed start-0 top-0 z-n1 opacity-50 hero-background text-white text-center d-flex flex-column justify-content-center align-items-center"></section>
      <MyNav />
      <Container className="text-center navbar-padding">
        <Card
          className="product-card mb-2 bg-light rounded-2 p-3 shadow border-0 w-25 mx-auto text-center"
          style={{ minWidth: "300px" }}
        >
          <img className="w-50 mx-auto " src={data.image} alt="immagine" />
          <p className="fw-bold">{data.title}</p>
          <p className="fw-bold">{data.price} €</p>
          <Button
            onClick={addToCartRedux}
            variant="light"
            className="w-100 rounded-2 border-0 text-white fw-bold cart-button"
          >
            Aggiungi al carrello
          </Button>
        </Card>
        <p className="mt-3 pt-3 fw-bold text-black "> {data.description}</p>
      </Container>
      <MyFooter />
    </>
  );
}

export default ProductDetails;
