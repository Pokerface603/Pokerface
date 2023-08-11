import Button from "@component/Button";
import React from "react";

const HistoryHeader = ({ myName, opponentName }) => {
  return (
    <div className="w-full h-32 bg-red-900 bg-opacity-75 rounded-xl text-center sticky top-0 z-10">
      <div
        className="relative flex justify-center w-full"
        style={{
          fontFamily: "NexonGothic",
          fontSize: "30px",
          fontWeight: "bold",
        }}>
        <p className="tracking-widest mt-10">
          {myName} vs {opponentName}
        </p>
        <div className="absolute right-3 top-3">
          <Button
            width="130px"
            height="50px"
            background="#c59c70"
            fontSize="30px"
            color="black"
            label="back"
          />
        </div>
      </div>
    </div>
  );
};

export default HistoryHeader;
