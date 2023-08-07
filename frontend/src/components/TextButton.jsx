import React from "react";
import kakaoLogo from "../assets/images/kakao-logo.svg";

const TextButton = ({ width, height, fontSize, text, imgSrc, onClick }) => {
  const isenglish = /^[A-Za-z]+$/;

  const fontFamily = isenglish.test(text)
    ? "Unchained-Spaghetti"
    : "NexonGothic";

  return (
    <div
      className="flex text-center justify-center items-center cursor-pointer hover:shadow-2xl hover:underline hover:opacity-75"
      style={{ width, height }}
      onClick={onClick}>
      {imgSrc && <img src={imgSrc} alt="logo" />}
      <span className=" align-bottom" style={{ fontFamily, fontSize }}>
        {text}
      </span>
    </div>
  );
};

export default TextButton;
