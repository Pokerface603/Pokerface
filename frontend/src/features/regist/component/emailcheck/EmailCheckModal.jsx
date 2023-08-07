import Input from "@component/Input";

const EmailCheck = ({ width, height, fontSize, onClick }) => {
  return (
    <div
      className="bg-email-check-ticket bg-cover fixed flex top-1/2 right-20"
      style={{ width, height }}>
      <div className="flex align-middle items-center justify-center w-8/12 h-full">
        <Input
          type="text"
          placeholder="E-mail code"
          width="222px"
          height="48px"
        />
      </div>
      <div className="flex align-middle items-center justify-center w-4/12 h-full">
        <span
          className="transform -rotate-90 hover:underline hover:opacity-80 cursor-pointer"
          style={{ fontFamily: "Unchained-Spaghetti", fontSize }}
          onClick={onClick}>
          submit
        </span>
      </div>
    </div>
  );
};

export default EmailCheck;
