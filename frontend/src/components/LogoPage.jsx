import React from "react";
import LogoGif from "../../src/assets/images/logo.gif";
import Parchment from "./Parchment";
import WoodBackground from "./WoodBackground";
import { useNavigate } from "react-router-dom";
import { useEffect } from "react";

const LogoPage = () => {
  const navigate = useNavigate();

  useEffect(() => {
    // 5초 후에 로그인 페이지로 이동
    const timeout = setTimeout(() => {
      navigate("/login");
    }, 4000);

    return () => {
      clearTimeout(timeout);
    };
  }, [navigate]);

  return (
    <>
      <WoodBackground>
        <Parchment style={{ height: "900px", width: "680px" }}>
          <div className="flex justify-center items-center h-full">
            <img src={LogoGif} alt="logo" />
          </div>
        </Parchment>
      </WoodBackground>
    </>
  );
};

export default LogoPage;
