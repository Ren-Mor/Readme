import { Container, Form, Button } from "react-bootstrap";
import { useState } from "react";
import { useSelector } from "react-redux";

function BackOffice() {
  const token = useSelector((state) => state.login.token);
  const createProductApi = "http://localhost:8080/products";
  const [loading, setLoading] = useState(false);
  async function handleAddProduct(e) {
    e.preventDefault();
    const formData = e.target.elements;
    const nome = formData.nome.value;
    const descrizione = formData.descrizione.value;
    const prezzo = formData.prezzo.value;
    const immagine = formData.immagine.value;
    const categoria = formData.categoria.value;

    setLoading(true);
    try {
      const res = await fetch(createProductApi, {
        method: "POST",
        headers: {
          "Content-Type": "application/json",
          Authorization: `Bearer ${token}`,
        },
        body: JSON.stringify({
          nome,
          descrizione,
          prezzo,
          immagine,
          categoria,
        }),
      });
      if (res.ok) {
        alert("Prodotto aggiunto con successo");
        await res.json();
      } else {
        const errorData = await res.json().catch(() => null);
        alert(
          errorData && errorData.message
            ? `Errore nell'inserimento: ${errorData.message}`
            : "Errore generico nell'inserimento"
        );
      }
    } catch (error) {
      alert("Errore di rete: " + error.message);
    } finally {
      setLoading(false);
    }
  }
  return (
    <>
      <section className="position-fixed start-0 top-0 z-n1 opacity-50 hero-background text-white text-center d-flex flex-column justify-content-center align-items-center"></section>
      <Container className="d-flex justify-content-center align-items-center vh-100">
        <Form onSubmit={handleAddProduct}>
          <Form.Label className="fw-bold">Nome</Form.Label>
          <Form.Control
            name="nome"
            type="text"
            placeholder="Nome"
            style={{ width: "300px" }}
            required
          />
          <Form.Label className="fw-bold">Descrizione</Form.Label>
          <Form.Control
            name="descrizione"
            type="text"
            placeholder="Descrizione"
            style={{ width: "300px" }}
            required
          />
          <Form.Label className="fw-bold">Prezzo</Form.Label>
          <Form.Control
            name="prezzo"
            type="text"
            placeholder="Prezzo"
            style={{ width: "300px" }}
            required
          />
          <Form.Label className="fw-bold">Immagine</Form.Label>
          <Form.Control
            name="immagine"
            type="immagine"
            placeholder="Immagine"
            style={{ width: "300px" }}
            required
          />
          <Form.Label className="mt-3 fw-bold">Categoria</Form.Label>
          <Form.Control
            name="categoria"
            type="categoria"
            placeholder="Categoria"
            required
          />
          <div className="d-flex">
            <Button
              disabled={loading}
              variant="danger"
              type="submit"
              className="w-100 mt-4 mx-auto"
              required
            >
              {loading ? "Aggiunta in corso..." : "Aggiungi Prodotto"}
            </Button>
          </div>
        </Form>
      </Container>
    </>
  );
}

export default BackOffice;
