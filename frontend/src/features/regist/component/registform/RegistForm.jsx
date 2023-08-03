import Input from "@component/Input";

import {
  height,
  nameWidth,
  pwWidth,
  checkWidth,
  idProps,
  nicknameProps,
  passwordProps,
  passCheckProps,
} from "./registFormStyle";

const RegistForm = () => {
  return (
    <div style={{ width: "912px", height: "428px" }}>
      <div className="flex">
        <Input
          width={nameWidth}
          height={height}
          type={idProps.type}
          placeholder={idProps.placeholder}
        />
        <div
          className="bg-check-button bg-cover cursor-pointer brightness-0 hover:brightness-50"
          style={{ width: checkWidth, height }}
        />
      </div>
      <div className="flex">
        <Input
          width={nameWidth}
          height={height}
          type={nicknameProps.type}
          placeholder={nicknameProps.placeholder}
        />
        <div
          className="bg-check-button bg-cover cursor-pointer brightness-0 hover:brightness-50"
          style={{ width: checkWidth, height }}
        />
      </div>
      <Input
        width={pwWidth}
        height={height}
        type={passwordProps.type}
        placeholder={passwordProps.placeholder}
      />
      <Input
        width={pwWidth}
        height={height}
        type={passCheckProps.type}
        placeholder={passCheckProps.placeholder}
      />
    </div>
  );
};

export default RegistForm;
