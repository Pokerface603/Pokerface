import React from "react";
import Input from "@component/Input";
import TextButton from "@component/TextButton";
import KakaoLogo from "../../../assets/images/kakao-logo.svg";
import { useDispatch } from "react-redux";
import { loginUser } from "@store/userSlice";
import { login } from "../api/api";
import { useCallback, useState } from "react";
import { validateEmail } from "@util/emailValidation";
import { useNavigate } from "react-router-dom";

const LoginFormContent = () => {
  const dispatch = useDispatch();

  // 이메일, 비밀번호 확인 state
  const [email, setEmail] = useState("");
  const [password, setPassword] = useState("");

  // 오류 메시지 상태 저장 state
  const [emailMessage, setEmailMessage] = useState("");

  // 이메일 유효성 검사 state
  const [isEmail, setIsEmail] = useState(false);

  const navigate = useNavigate();
  const clickLogin = () => {
    if (!email) {
      return alert("이메일을 입력해주세요.");
    } else if (!password) {
      return alert("비밀번호를 입력해주세요.");
    } else if (isEmail === false) {
      return alert("이메일 형식을 확인해주세요.");
    } else {
      login(email, password)
        .then((response) => {
          // 로그인 성공
          if (response.status === 200) {
            dispatch(
              loginUser({
                nickname: response.data.nickname,
                email: response.data.email,
                authorization: response.headers["authorization"],
                authorizationRefresh: response.headers["authorization-refresh"],
              })
            );

            localStorage.setItem(
              "authorization",
              response.headers["authorization"]
            );

            navigate("/lobby");
          }
        })
        .catch((error) => {
          if (error.response.status === 400) {
            // 로그인 실패
            alert(
              "아이디 또는 비밀번호를 잘못 입력했습니다. \n입력하신 내용을 다시 확인해주세요."
            );
            setEmail("");
            setPassword("");
          }
        });
    }
  };

  // 카카오 로그인
  const kakaoLogin = () => {
    const kakaoAuthUrl = `${process.env.REACT_APP_API_URL}/oauth2/authorization/kakao`; // Kakao 로그인 HTTPS URL
    window.location.href = kakaoAuthUrl;
  };

  // const kakaoLogin = () => {
  //   const kakaoAuthUrl =
  //     "https://pokerface-server.ddns.net/api/oauth2/authorization/kakao"; // Kakao 로그인 URL
  //   window.location.href = kakaoAuthUrl;
  //   // const token = new URL(window.location.href).searchParams.get("accessToken");
  //   // console.log(token);
  //   // const refreshToken = new URL(window.location.href).searchParams.get(
  //   // "refreshToken"
  //   // );
  // };

  // 이메일 유효성 검사
  const onChangeEmail = useCallback((e) => {
    const emailCurrent = e.target.value;
    setEmail(emailCurrent);
    validateEmail(emailCurrent, setEmailMessage, setIsEmail);
  }, []);

  // 비밀번호 입력
  const onChangePassword = (e) => {
    setPassword(e.target.value);
  };

  return (
    <div className="h-1/3">
      <div className="flex flex-col items-center justify-center">
        <form>
          <Input
            width={"282px"}
            height={"59px"}
            placeholder={"ID(E-MAIL)"}
            type="email"
            id="email"
            value={email}
            onChange={onChangeEmail}
          />
          {email.length > 0 && (
            <span
              style={{
                fontFamily: "NexonGothic",
                marginLeft: "13px",
                color: `${isEmail ? "green" : "red"}`,
              }}
            >
              {emailMessage}
            </span>
          )}
          <Input
            width="282px"
            height="59px"
            placeholder="PASSWORD"
            type="password"
            id="password"
            value={password}
            onChange={onChangePassword}
          />
          <div className="flex items-center justify-center mt-3 gap-7">
            <TextButton
              width="82px"
              height="28px"
              fontSize="28px"
              text="로그인"
              onClick={clickLogin}
            />
            <p style={{ fontSize: "22px" }}>|</p>
            <TextButton
              width="99px"
              height="25px"
              fontSize="28px"
              text="회원가입"
              onClick={() => navigate("/regist")}
            />
          </div>
          <div className="flex  items-center justify-center mt-3">
            <TextButton
              width="99px"
              height="25px"
              fontSize="25px"
              text="KAKAO"
              imgSrc={KakaoLogo}
              onClick={kakaoLogin}
            />
          </div>
        </form>
      </div>
    </div>
  );
};

export default LoginFormContent;
