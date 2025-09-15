import { Button, Col, Card } from "react-bootstrap";
import { useNavigate } from "react-router";
import { useDispatch } from "react-redux";
import "../style/SingleProduct.css";

const SingleProduct = ({ data }) => {
  const navigate = useNavigate();
  const dispatch = useDispatch();
  const addToCartRedux = () => {
    dispatch({ type: "ADD_TO_CART", payload: data });
  };

  return (
    <Col key={data.id}>
      <Card className="product-card mb-2 bg-light rounded-2 p-3 shadow border-0">
        <img
          className="w-100 "
          style={{ height: "190px", objectFit: "contain" }}
          src={data.image}
          alt="immagine"
          onClick={() => {
            navigate("/details/" + data.id, { state: { data } });
          }}
        />
        <p className="text-truncate">{data.title}</p>
        <p>{data.price} €</p>
        <Button
          onClick={addToCartRedux}
          variant="light"
          className="w-100 rounded-2 border-0 text-white fw-bold cart-button"
        >
          Aggiungi al carrello
        </Button>
      </Card>
    </Col>
  );
};

export default SingleProduct;
