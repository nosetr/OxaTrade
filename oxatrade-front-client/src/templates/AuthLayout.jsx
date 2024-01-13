/**
 * <AuthLayout /> component will wrap the outlet element with AuthProvider.
 * This will enable all the child Routes to have access to auth context:
 */
import React, { useEffect, useState, Suspense } from "react";
import { useLoaderData, useOutlet, Await } from "react-router-dom";
import { AuthProvider } from "../hooks/useAuth";
import {
    Progress
} from 'reactstrap';
import ToastMsg from "../components/ToastMsg"
import { toast } from 'react-toastify'

export const AuthLayout = () => {

    const [progress, setProgress] = useState(0);

    useEffect(() => {
        setInterval(() => {
            setProgress((val) => val + 1);
        }, 100);
    }, []);

    const outlet = useOutlet();

    const { userPromise } = useLoaderData();

    return (
        <Suspense fallback={
            <Progress
                style={{ height: '3px' }}
                value={progress}
                color="danger" />}>
            <Await
                resolve={userPromise}
                errorElement={<ToastMsg text={'An error occurred. Please try again later.'} />}
                // errorElement={toast.error('An error occurred. Please try again later.', {
                //     position: "bottom-right",
                //     // autoClose: false,
                //     // hideProgressBar: false,
                //     // closeOnClick: false,
                //     pauseOnHover: true,
                //     draggable: true,
                //     // progress: 0,
                //   })}
                children={(user) => (
                    <AuthProvider userData={user}>{outlet}</AuthProvider>
                )}
            />
        </Suspense>
    );
};