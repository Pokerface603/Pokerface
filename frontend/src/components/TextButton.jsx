import React from "react";
import kakaoLogo from "../assets/images/kakao-logo.svg";

const TextButton = ({ width, height, fontSize, text, onClick }) => {
  const isKakao = text === "kakao";

  const font = isKakao ? "Unchained-Spaghetti" : "NexonGothic";

  return (
    <div
      className="flex text-center justify-center items-center cursor-pointer hover:shadow-2xl hover:underline hover:opacity-75"
      style={{ width, height }}
      onClick={onClick}>
      {isKakao && <img src={kakaoLogo} alt="l" />}
      <span className=" align-bottom" style={{ fontFamily: font, fontSize }}>
        {text}
      </span>
    </div>
  );
};

export default TextButton;
