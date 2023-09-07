import React from "react";
import { MDBInput, MDBCheckbox, MDBBtn } from "mdb-react-ui-kit";
import { toast,ToastContainer } from "react-toastify";
import 'react-toastify/dist/ReactToastify.css'

export default function LoginLayout() {
  var isLogin = false;
  const loginSubmit = ()=>{
    isLogin ? toast.success("dang nhap thanh cong") : toast.warning("dang nhap ko thanh cong");

  }
  return (
    <div>
      <form>
        <MDBInput className="mb-4" id="name" label="Tên đăng nhập" />
        <MDBInput
          className="mb-4"
          type="password"
          id="form5Example2"
          label="Mật khẩu"
        />
        <MDBBtn type="submit" onClick={loginSubmit}>Đăng nhập</MDBBtn>
      </form>

      <ToastContainer
        position="top-center"
        autoClose={5000}
        hideProgressBar
        newestOnTop
        closeOnClick
        rtl={false}
        pauseOnFocusLoss
        draggable
        pauseOnHover
        theme="light"
      />
    </div>
  );
}
