import { sequelize } from '../database'

const { User } = sequelize.models
const { Op } = sequelize

export const userService = {
    async authenticate(username: string, password: string) {
        const user = await User.findOne({
            where: { name: username },
            raw: true,
        })
        return user
    },
    async register({ username, age, password }) {
        const user = await User.findOne({
            where: { name: username },
            raw: true,
        })
        if (user) {
            throw '用户重复了！'
        }
        await User.create({ name: username, age, password })
    },
    async list({ page, size, username }) {
        const limit = size || 10
        const offset = page ? (page - 1) * limit : 0
        const where: any = {}
        if (username) {
            where.name = { [Op.like]: `%${username.trim()}%` }
        }
        return await User.findAll({
            where,
            limit,
            offset,
            raw: true
        })
    }
}
