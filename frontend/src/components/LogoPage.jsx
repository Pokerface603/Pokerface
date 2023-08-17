import React, { useState, useEffect } from "react";
import LogoGif from "../../src/assets/images/logo.gif";
import Parchment from "./Parchment";
import WoodBackground from "./WoodBackground";
import { useNavigate } from "react-router-dom";

const LogoPage = () => {
  const navigate = useNavigate();
  const [showImage, setShowImage] = useState(false);

  useEffect(() => {
    const imageTimeout = setTimeout(() => {
      setShowImage(true);
    }, 500);

    const loginTimeout = setTimeout(() => {
      // 로그인으로 이동
      navigate("/login");
    }, 4000);

    return () => {
      clearTimeout(imageTimeout);
      clearTimeout(loginTimeout);
    };
  }, [navigate]);

  return (
    <WoodBackground>
      <Parchment style={{ height: "900px", width: "680px" }}>
        <div className="flex justify-center items-center h-full">
          {showImage && <img src={LogoGif} alt="logo" />}
        </div>
      </Parchment>
    </WoodBackground>
  );
};

export default LogoPage;
