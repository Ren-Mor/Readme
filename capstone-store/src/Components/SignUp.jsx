import { Container, Form, Button } from "react-bootstrap";
import { useState } from "react";

function SignUp() {
  const signUpApi = "http://localhost:8080/auth/register";
  const [loading, setLoading] = useState(false);

  async function handleSignup(e) {
    e.preventDefault();
    const formData = e.target.elements;
    const username = formData.username.value;
    const nome = formData.nome.value;
    const cognome = formData.cognome.value;
    const email = formData.email.value;
    const password = formData.password.value;
    const confirmPassword = formData["confirm-password"].value;

    if (password !== confirmPassword) {
      alert("Le password non coincidono.");
      return;
    }

    setLoading(true);
    try {
      const res = await fetch(signUpApi, {
        method: "POST",
        headers: { "Content-Type": "application/json" },
        body: JSON.stringify({ username, nome, cognome, email, password }),
      });
      if (res.ok) {
        alert("Registrazione avvenuta con successo");
        await res.json();
      } else {
        const errorData = await res.json().catch(() => null);
        alert(
          errorData && errorData.message
            ? `Errore nella registrazione: ${errorData.message}`
            : "Errore nella registrazione"
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
        <Form onSubmit={handleSignup}>
          <Form.Label className="fw-bold">Nome</Form.Label>
          <Form.Control
            name="nome"
            type="text"
            placeholder="Nome"
            style={{ width: "300px" }}
            required
          />
          <Form.Label className="fw-bold">Cognome</Form.Label>
          <Form.Control
            name="cognome"
            type="text"
            placeholder="Cognome"
            style={{ width: "300px" }}
            required
          />
          <Form.Label className="fw-bold">Username</Form.Label>
          <Form.Control
            name="username"
            type="text"
            placeholder="Username"
            style={{ width: "300px" }}
            required
          />
          <Form.Label className="fw-bold">Email</Form.Label>
          <Form.Control
            name="email"
            type="email"
            placeholder="Email"
            style={{ width: "300px" }}
            required
          />
          <Form.Label className="mt-3 fw-bold">Password</Form.Label>
          <Form.Control
            name="password"
            type="password"
            placeholder="Password"
            required
          />
          <Form.Label className="mt-3 fw-bold">Conferma password</Form.Label>
          <Form.Control
            name="confirm-password"
            type="password"
            placeholder="Password"
            required
          />
          <div className="d-flex">
            <Button
              variant="danger"
              type="submit"
              className="w-100 mt-4 mx-auto"
              disabled={loading}
            >
              {loading ? "Registrazione..." : "Registrati"}
            </Button>
          </div>
          <div className="d-flex fw-bold justify-content-center mt-2">
            <p>
              Hai già un account?{" "}
              <a href="/signin" className="text-decoration-none text-danger ">
                Accedi
              </a>
            </p>
          </div>
        </Form>
      </Container>
    </>
  );
}

export default SignUp;
