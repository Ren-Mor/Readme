import { Container, Form, Button } from "react-bootstrap";
import { useNavigate } from "react-router-dom";
import { useState } from "react";
import { useDispatch } from "react-redux";

function SignIn() {
  const navigate = useNavigate();
  const dispatch = useDispatch();
  const currentUserApi = "http://localhost:8080/utenti/me";
  const [loading, setLoading] = useState(false);
  const saveToken = (token) => {
    localStorage.setItem("loginToken", token);
  };
  const saveUser = (user) => {
    localStorage.setItem("loginUser", JSON.stringify(user));
  };

  async function setUser() {
    const token = localStorage.getItem("loginToken");

    try {
      const res = await fetch(currentUserApi, {
        method: "GET",
        headers: {
          "Content-Type": "application/json",
          Authorization: `Bearer ${token}`,
        },
      });
      if (res.ok) {
        const data = await res.json();
        dispatch({ type: "SET_LOGIN", payload: { user: data } });
        saveUser(data);
        console.log(data);
        console.log(JSON.parse(localStorage.getItem("loginUser")));
      }
    } catch (error) {
      console.error("Errore nella richiesta del profilo utente:", error);
    }
  }

  async function handleSignin(e) {
    e.preventDefault();
    const signInApi = "http://localhost:8080/auth/login";
    const formData = e.target.elements;
    const email = formData.email.value;
    const password = formData.password.value;

    setLoading(true);
    try {
      const res = await fetch(signInApi, {
        method: "POST",
        headers: { "Content-Type": "application/json" },
        body: JSON.stringify({ email, password }),
      });
      if (res.ok) {
        alert("Accesso effettuato con successo");
        const data = await res.json();
        console.log(data);
        dispatch({
          type: "SET_LOGIN",
          payload: { token: data.accessToken, user: data.user },
        });
        saveToken(data.accessToken);
        setUser();
        navigate("/userprofile");
      } else {
        const errorData = await res.json().catch(() => null);
        alert(
          errorData && errorData.message
            ? `Errore nell'accesso: ${errorData.message}`
            : "Errore nell'accesso"
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
        <Form onSubmit={handleSignin}>
          <Form.Label className="fw-bold">Email o Username</Form.Label>
          <Form.Control
            name="email"
            type="email"
            placeholder="Email o Username"
            required
            style={{ width: "300px" }}
          />
          <Form.Label className="mt-3 fw-bold">Password</Form.Label>
          <Form.Control
            name="password"
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
              {loading ? "Accesso..." : "Accedi"}
            </Button>
          </div>
          <div className="d-flex fw-bold justify-content-center mt-2">
            <p>
              Non hai un account?{" "}
              <a href="/signup" className="text-decoration-none text-danger ">
                Registrati
              </a>
            </p>
          </div>
        </Form>
      </Container>
    </>
  );
}

export default SignIn;
