import React from "react";

const TextButton = ({ width, height, fontSize, text }) => {
  return (
    <div
      className="text-center cursor-pointer hover:underline hover:shadow-2xl hover:opacity-75"
      style={{ width, height }}>
      <span
        className=" align-bottom"
        style={{ fontFamily: "NexonGothic", fontSize }}>
        {text}
      </span>
    </div>
  );
};

export default TextButton;
