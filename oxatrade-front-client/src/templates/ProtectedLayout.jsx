import { Navigate, Outlet } from "react-router-dom";
import Header from "../components/Header";

import { useAuth } from "../hooks/useAuth";

const ProtectedLayout = () => {

  if (typeof useAuth() == 'undefined'){
    return <Navigate to="/login" />;
  }

  // const { user } = useAuth();
  // if (!user) {
  //   return <Navigate to="/login" />;
  // }

  return (
    <>
      <Header />
      <main>
        <Outlet />
      </main>
    </>
  )
}

export default ProtectedLayout;