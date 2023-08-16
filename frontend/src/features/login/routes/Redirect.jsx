import { loginUser } from "@store/userSlice";
import { useDispatch } from "react-redux";
import { Navigate } from "react-router";

function Redirect() {
  const dispatch = useDispatch();

  const params = new URLSearchParams(window.location.search);

  const accessToken = params.get("accessToken");
  const refreshToken = params.get("refreshToken");
  const email = params.get("email");
  const nickname = decodeURI(params.get("nickname"));

  localStorage.setItem("authorization", "Bearer " + accessToken);

  dispatch(
    loginUser({
      authorization: accessToken,
      authorizationRefresh: refreshToken,
      email,
      nickname,
    })
  );

  return <Navigate to="/lobby"></Navigate>;
}

export default Redirect;
