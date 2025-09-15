import { Row, Col, Container, Form, Button } from "react-bootstrap";

function Checkout() {
  return (
    <>
      <section className="position-fixed start-0 top-0 z-n1 opacity-50 hero-background text-white text-center d-flex flex-column justify-content-center align-items-center"></section>
      <Container className="d-flex justify-content-center align-items-center vh-100">
        <Form>
          <Form.Label className="fw-bold">Numero carta</Form.Label>
          <Form.Control
            type="text"
            placeholder="Numero carta"
            required
            style={{ width: "300px" }}
          />
          <Form.Label className="fw-bold">Scadenza</Form.Label>
          <Form.Control
            type="text"
            placeholder="MM/AA"
            required
            style={{ width: "300px" }}
          />
          <Form.Label className="mt-3 fw-bold">
            Codice di sicurezza (CVV)
          </Form.Label>
          <Form.Control type="text" placeholder="CVV" required />
          <Form.Label className="mt-3 fw-bold">Nome intestatario</Form.Label>
          <Form.Control type="text" placeholder="Nome intestatario" required />
          <div className="d-flex">
            <Button
              variant="danger"
              type="submit"
              className="w-100 mt-4 mx-auto"
            >
              Procedi al pagamento
            </Button>
          </div>
        </Form>
      </Container>
    </>
  );
}

export default Checkout;
