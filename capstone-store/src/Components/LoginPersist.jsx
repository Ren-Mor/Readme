import { useEffect } from "react";
import { useDispatch } from "react-redux";

function LoginPersist() {
  const dispatch = useDispatch();
  useEffect(() => {
    const token = localStorage.getItem("loginToken");
    if (token) {
      dispatch({ type: "SET_LOGIN", payload: { token: token } });
    }
  });
  useEffect(() => {
    const user = JSON.parse(localStorage.getItem("loginUser"));
    if (user) {
      dispatch({ type: "SET_LOGIN", payload: { user: user } });
    }
  });

  return null;
}

export default LoginPersist;
