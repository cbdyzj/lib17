export function withErrorHandling(getServerSideProps) {
    return async (context) => {
        try {
            return await getServerSideProps(context)
        } catch (err) {
            return {
                redirect: {
                    destination: `/error?message=${encodeURIComponent(err.message)}`,
                    permanent: false,
                }
            }
        }
    }
}