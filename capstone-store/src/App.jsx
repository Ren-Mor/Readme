import { BrowserRouter, Routes, Route } from "react-router-dom";
import { useEffect, useState } from "react";
import MyNav from "./Components/MyNav";
import Hero from "./Components/Hero";
import Cart from "./Components/Cart";
import MyFooter from "./Components/MyFooter";
import ProductList from "./Components/ProductList";
import ProductDetails from "./Components/ProductDetails";
import SignIn from "./Components/SignIn";
import SignUp from "./Components/SignUp";
import Checkout from "./Components/Checkout";
import BackOffice from "./Components/BackOffice";
import UserProfile from "./Components/UserProfile";
import "./App.css";
import LoginPersist from "./Components/LoginPersist";

function App() {
  const [products, setProducts] = useState([]);
  useEffect(() => {
    fetch("https://fakestoreapi.com/products")
      .then((response) => {
        if (!response.ok) {
          throw new Error("Impossibile caricare i prodotti");
        }
        return response.json();
      })
      .then((data) => {
        setProducts(data);
      })
      .catch((error) => {
        console.error("Errore nel recupero dei prodotti:", error);
      });
  }, []);
  return (
    <>
      <BrowserRouter>
        <LoginPersist />
        <MyNav />

        <Routes>
          <Route path="/" element={<Hero />} />
          <Route path="/cart" element={<Cart />} />
          <Route
            path="/prodotti"
            element={<ProductList categoria={products} />}
          />
          <Route path="/details/:productId" element={<ProductDetails />} />
          <Route path="/signin" element={<SignIn />} />
          <Route path="/signup" element={<SignUp />} />
          <Route path="/cart/checkout" element={<Checkout />} />
          <Route path="/userprofile" element={<UserProfile />} />
          <Route path="/backoffice" element={<BackOffice />} />
        </Routes>
        <MyFooter />
      </BrowserRouter>
    </>
  );
}

export default App;
