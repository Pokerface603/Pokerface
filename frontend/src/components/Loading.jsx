import React from "react";
import Spinner from "../../src/assets/images/spiner.gif";

const Loading = () => {
  return (
    <div className="fixed inset-0 bg-white bg-opacity-70 flex items-center justify-center z-50">
      <div className="w-screen h-screen flex flex-col items-center justify-center">
        <h1
          className="animate-text bg-gradient-to-r from-orange-700 via-amber-600 to-green-900 bg-clip-text text-transparent font-black mb-4"
          style={{ fontFamily: "BouWestern", fontSize: "40px" }}
        >
          please wait for a moment
        </h1>
        <img src={Spinner} alt="로딩중" width="15%" />
      </div>
    </div>
  );
};

export default Loading;
