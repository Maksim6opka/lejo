
---

# 🧩 Lejo — Plugin for Custom Join/Quit Messages

**Lejo** is a lightweight and flexible plugin for Minecraft (Paper/Purpur) that allows full customization of join and quit messages. It supports gradients, Hex colors, formatting with placeholders, and logic based on permissions, world, nickname, and more.

---

## 🔧 Main Features

### ✅ 1. Join/Quit Messages

The plugin sends custom chat messages when players:

* **join** the server
* **leave** the server

### ✅ 2. Group-Based Messages (`parent`)

You can define different message templates for specific permission groups like `admin`, `prime`, etc.

### ✅ 3. Placeholders Support

| Placeholder             | Description                               |
| ----------------------- | ----------------------------------------- |
| `{p}`                   | Player's name (nickname or display name)  |
| `{t}`                   | Current server time (customizable format) |
| `{w}`                   | World name the player joined or left from |
| `{w}`                   | Alias for world name                      |

### ✅ 4. Gradient & Color Formatting

You can use:

* **Hex colors**: `<color:#FF00FF>text</color>`
* **Gradients**: `<gradient:#FF00FF:#00FFFF>text</gradient>`
* **Standard MiniMessage formatting** like `<bold>`, `<italic>`, etc.

### ✅ 5. Priority System

Messages are selected based on the following order:

1. **Nickname**
2. **Parent/Permission**
3. **Default**

---

## 📄 `config.yml` Structure

```yaml
messages:
  default:
    join: "[...]"
    leave: "[...]"
  permission:
    admin:
      join: "[...]"
      leave: "[...]"
    prime:
      join: "[...]"
      leave: "[...]"
  nickname:
    SomePlayer:
      join: "[...]"
      leave: "[...]"
  system:
    enable: "Lejo plugin enabled"
    disable: "Lejo plugin disabled"
    reload: "Lejo plugin reload"
  placeholders:
    time-format: "HH:mm:ss"
    date-format: "dd.MM.yyyy"
    location-format: "x:{x} y:{y} z:{z}"
    worlds:
      nether: "nether"
      overworld: "overworld"
      end: "end"
      custom: "custom"
prefix:
  suffix-mode: false
  join: [+]
  leave: [-]
```

---

## 🔌 Commands

| Command         | Description               | Permission     |
| --------------- | ------------------------- | -------------- |
| `/lejo reload`  | Reloads the plugin config | `lejo.reload`  |

---

## 💡 Example Join Message

```yaml
join:
  - "Welcome <gradient:#00FF00:#007F00>{p}</gradient> to <color:#AAAAAA>{w}</color> at {t}!"
```

→ Player **John** joined the `world_nether` at `13:42:17`
**Chat Output:** `Welcome John to world at 13:42:17!`

---

## 🧠 Additional Features

* MiniMessage support (gradients, hex colors, formatting)
* Clean, separate logic for message generation
* Priority logic: `nickname > parent > default`
