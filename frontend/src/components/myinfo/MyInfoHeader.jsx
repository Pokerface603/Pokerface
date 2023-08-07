import React from "react";

const MyInfoHeader = ({ nickname, button }) => {
  return (
    <div className="flex mt-2 justify-between">
      {nickname && (
        <span
          className="mx-3"
          style={{
            fontFamily: "NexonGothic",
            fontSize: "30px",
            marginBottom: "-1rem",
          }}
        >
          {nickname}
        </span>
      )}
      {button}
    </div>
  );
};

export default MyInfoHeader;
