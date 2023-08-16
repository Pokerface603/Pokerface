import React from "react";

const TextButton = ({ width, height, fontSize, text, imgSrc, onClick }) => {
  const isenglish = /^[A-Za-z]+$/;

  const fontFamily = isenglish.test(text)
    ? "Unchained-Spaghetti"
    : "NexonGothic";

  return (
    // testButton에 애니메이션 추가
    <div
      className="transition ease-in-out delay-100 hover:-translate-y-0.5 hover:scale-105 duration-200 flex text-center justify-center items-center cursor-pointer hover:shadow-2xl hover:underline hover:opacity-75"
      style={{ width, height }}
      onClick={onClick}
    >
      {imgSrc && <img src={imgSrc} alt="logo" />}
      <span className=" align-bottom" style={{ fontFamily, fontSize }}>
        {text}
      </span>
    </div>
  );
};

export default TextButton;
