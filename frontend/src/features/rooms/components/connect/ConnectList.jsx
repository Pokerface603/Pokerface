import React, { useContext, useEffect, useState } from "react";
import FrameButton from "./FrameButton";
import AmericanSaloon from "../../../../assets/images/rooms/american-saloon.svg";
import { getFriends, getLobbies } from "@feature/rooms/api/connect";
import OnlineMemberItem from "./OnlineMemberItem";
import { useSelector } from "react-redux";
import OnlineFriendItem from "./OnlineFriendItem";
import { WebSocketContext } from "context/WebsocketProvider";

const ConnectList = () => {
  const [selectedTab, setSelectedTab] = useState("CONNECT");
  const [memberList, setMemberList] = useState([]);

  const { email } = useSelector((state) => state.user);

  const ws = useContext(WebSocketContext);

  ws.current.onmessage = (e) => {
    const { data } = e;
    const [cmd, reqEmail, nickname] = data.split(",");

    if (cmd === "REQUEST") {
      const ret = window.confirm(
        `${nickname}님이 친구요청을 하셨습니다. 수락하시겠습니까?`
      );

      if (ret) {
        ws.current.send(`RESPONSE,${reqEmail}`);
      }
    } else if (cmd === "UPDATE") {
      fetchMembers();
    }
  };

  const fetchMembers = async () => {
    if (selectedTab === "CONNECT") {
      const userList = await getLobbies(email);
      setMemberList(userList);
    } else {
      const userList = await getFriends(email);
      setMemberList(userList);
    }
  };

  useEffect(() => {
    fetchMembers();
  }, [selectedTab]);

  return (
    <div className="relative w-600 h-200">
      <div className="flex">
        <div className="flex-1 relative flex justify-center items-center mt-4">
          <FrameButton
            text="CONNECT"
            onClick={setSelectedTab}
            tab={selectedTab}
          />
        </div>
        <div className="flex-1 relative flex justify-center items-center mt-4">
          <FrameButton
            text="FRIENDS"
            onClick={setSelectedTab}
            tab={selectedTab}
          />
        </div>
      </div>
      <div className="flex items-center justify-center">
        <div
          className="grid grid-cols-6 gap-2 items-center"
          style={{ width: "380px", height: "43px" }}
        >
          {selectedTab === "CONNECT"
            ? memberList.map((member) => {
                const { tier, nickname, email, isFriend } = member;
                return (
                  <OnlineMemberItem
                    key={nickname}
                    tier={tier}
                    nickname={nickname}
                    email={email}
                    isFriend={isFriend}
                  />
                );
              })
            : memberList.map((friend) => {
                const { tier, friendNickname, roomInfoRes } = friend;
                return (
                  <OnlineFriendItem
                    key={friendNickname}
                    tier={tier}
                    nickname={friendNickname}
                    roomInfoRes={roomInfoRes}
                  />
                );
              })}
        </div>
      </div>
      <img
        src={AmericanSaloon}
        style={{
          top: "90px",
          right: "150px",
          position: "absolute",
          zIndex: 1,
        }}
        alt="american-saloon"
      />
    </div>
  );
};

export default ConnectList;
