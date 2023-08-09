import React, { useEffect, useState } from "react";
import Parchment from "@component/Parchment";
import WoodBackground from "@component/WoodBackground";
import Button from "@component/Button";
import MyInfo from "@component/myinfo/MyInfo";
import StartButton from "./StartButton";
import Logo from "./Logo";
import SearchBar from "./SearchBar";
import Ranking from "./rank/Ranking";
import ConnectList from "./connect/ConnectList";
import Tab from "./Tab";
import RoomMakeModal from "./RoomMakeModal";
import { getRooms } from "../api/room";
import Room from "./RoomCard/RoomCard";

const RoomsPage = () => {
  const [showRoomMakeModal, setShowRoomMakeModal] = useState(false);
  const [rooms, setRooms] = useState([]);

  const onClickMakeRoom = () => {
    setShowRoomMakeModal(true);
  };

  const closeModal = () => {
    setShowRoomMakeModal(false);
  };

  useEffect(() => {
    async function fetchRoomData() {
      const rooms = await getRooms("NORMAL");
      setRooms(rooms);
    }

    fetchRoomData();
  }, []);

  return (
    <WoodBackground>
      <div className="grid grid-flow-col gap-4">
        <div className="col-span-1">
          <div className style={{ width: "1280px", height: "870px" }}>
            <div className="h-1/4">
              <div className="grid grid-cols-3 gap-10 h-full">
                <div className="col-span-2 grid mb-3">
                  <Logo />
                  <div style={{ width: "660px" }}>
                    <SearchBar />
                  </div>
                </div>
                <div>
                  <StartButton onClickCreateRoom={onClickMakeRoom} />
                </div>
              </div>
            </div>

            <Parchment
              style={{ width: "1280px", height: "652px", display: "flex" }}
            >
              <div className="flex flex-col justify-center items-center mx-auto">
                <div
                  style={{
                    width: "1225px",
                    height: "105px",
                    background: "var(--ocher)",
                  }}
                >
                  <Tab />
                </div>

                {/* 방 정보들이 들어가야하는 부분 */}
                <div
                  style={{
                    width: "1225px",
                    height: "520px",
                    background: "var(--ocher)",
                    display: "flex",
                    flexWrap: "wrap",
                    alignContent: "start",
                    padding: "15px",
                    gap: "10px",
                  }}
                  className="justify-between"
                >
                  {rooms.map((room) => (
                    <Room {...room} />
                  ))}
                </div>
              </div>
            </Parchment>
          </div>
        </div>
        <div className="col-span-1">
          <div
            style={{
              width: "510px",
              height: "870px",
            }}
          >
            <div className="h-1/5 flex justify-center items-center">
              {/* 임의의 값 입력 */}
              <MyInfo
                width="450px"
                height="160px"
                tier="A"
                nickname="닉네임"
                reward="999"
                wins="2000"
                totalMatches="4000"
                button={
                  <Button
                    width="103px"
                    height="35px"
                    label="내 정보"
                    background="white"
                  />
                }
              />
            </div>
            <div className="h-2/5 flex justify-center items-center">
              <Parchment style={{ width: "450px", height: "320px" }}>
                <div className="mt-5">
                  {/* 임의의 값 입력 */}
                  <Ranking nickname={"Hanna"} tier="Joker" reward={"13000"} />
                </div>
              </Parchment>
            </div>
            <div className="h-2/5 flex justify-center items-center">
              <Parchment style={{ width: "450px", height: "320px" }}>
                <div className="mt-1">
                  {/* 임의의 값 입력 */}
                  <ConnectList
                    tier="J"
                    nickname="Nana"
                    button={
                      <Button
                        width="130px"
                        height="30px"
                        label="같이하기"
                        background="var(--brown_dark)"
                        color="white"
                      />
                    }
                  />
                </div>
              </Parchment>
            </div>
          </div>
        </div>
      </div>
      {showRoomMakeModal && <RoomMakeModal close={closeModal} />}
    </WoodBackground>
  );
};

export default RoomsPage;
