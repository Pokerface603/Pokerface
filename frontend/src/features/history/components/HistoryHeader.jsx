import Button from "@component/Button";
import React from "react";
import { useNavigate } from "react-router-dom";

const HistoryHeader = ({ myName, opponentName }) => {
  const navigate = useNavigate();

  const onClickBack = () => {
    navigate("/mypage");
  };
  return (
    <div className="w-full h-32 bg-parchment-box bg-cover bg-opacity-75 shadow-xl rounded-xl text-center sticky top-0 z-10">
      <div
        className="relative flex justify-center w-full"
        style={{
          fontFamily: "NexonGothic",
          fontSize: "30px",
          fontWeight: "bold",
        }}>
        <p className="tracking-widest mt-10 mr-12">{myName}</p>
        <p className="tracking-widest mt-10">vs</p>
        <p className="tracking-widest mt-10 ml-12">{opponentName}</p>
        <div className="absolute right-3 top-3">
          <Button
            width="130px"
            height="50px"
            background="#c59c70"
            fontSize="30px"
            color="black"
            label="back"
            onClick={onClickBack}
          />
        </div>
      </div>
    </div>
  );
};

export default HistoryHeader;
